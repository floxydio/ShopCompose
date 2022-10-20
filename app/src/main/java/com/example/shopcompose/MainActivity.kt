package com.example.shopcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.shopcompose.ui.theme.ShopComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable(route ="home") {
            HomePages(navController)
        }
        composable(route="detail/{id}", arguments = listOf(navArgument("id") {
            type = NavType.StringType
        })) {
            DetailScreen(navController, it.arguments?.getString("id") ?: "")
        }
    }
}

@Composable
fun HomePages(navController: NavController) {
    Column() {
        Text("Home Screen")

        Button(onClick = {
            navController.navigate("detail/2")
        }) {
            Text(text = "Go to Another Page")
        }
    }
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
