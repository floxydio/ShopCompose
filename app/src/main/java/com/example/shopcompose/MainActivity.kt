package com.example.shopcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.shopcompose.models.DataModelElement
import com.example.shopcompose.ui.theme.ShopComposeTheme
import com.example.shopcompose.vm.MarketModel

class MainActivity : ComponentActivity() {
    val marketViewModel by viewModels<MarketModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(marketList = marketViewModel.marketDataRes)
                    marketViewModel.getMarketList()
                }
            }
        }
    }
}
@Composable
fun HomeScreen(marketList: List<DataModelElement>) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable(route ="home") {
            HomePages(navController,marketList)
        }
        composable(route="detail/{id}", arguments = listOf(navArgument("id") {
            type = NavType.StringType
        })) {
            DetailScreen(navController, it.arguments?.getString("id") ?: "")
        }
    }
}


@Composable
fun HomePages(navController: NavController, marketList: List<DataModelElement>) {
    Column (modifier = Modifier.padding(top=20.dp, start = 15.dp, end = 15.dp)) {
        CardTheme()
        Spacer(modifier = Modifier.height(10.dp))
        SearchPage()
        MarketList(marketList = marketList )

//        Button(onClick = {
//            navController.navigate("detail/2")
//        }) {
//            Text(text = "Go to Another Page")
//        }
    }
}


@Composable
fun MarketList(marketList: List<DataModelElement>) {
    LazyColumn {
        itemsIndexed(items = marketList) {
            index, item -> Card(modifier = Modifier.fillMaxWidth()) {
                Column() {
                    Text(item.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier= Modifier.height(10.dp))
                    Text(item.description, fontSize = 14.sp)
                }

        }
        }
    }
}

@Composable
fun CardTheme() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier.padding(start=20.dp,end=20.dp, bottom = 30.dp, top = 30.dp)
        ) {
            Text("Hello Dio Rovelino",fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text("Let's Find What you needed", fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun SearchPage() {
    var searchMarket by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
          Icon(Icons.Outlined.Search, contentDescription = null)
        },
        value = searchMarket , onValueChange = {
        searchMarket = it
    } )
}

@Composable
fun DetailScreen(navController: NavController, s: String,) {
    Column() {
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
        Text(s)
        Text("Detail Screen")
    }
}
