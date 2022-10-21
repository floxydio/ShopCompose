package com.example.shopcompose.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopcompose.models.DataModelElement
import com.example.shopcompose.network.Apiinterface
import kotlinx.coroutines.launch

class MarketModel : ViewModel()  {
    var marketDataRes : List<DataModelElement> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getMarketList() {
        viewModelScope.launch {
            val apiService = Apiinterface.getInstance()
            try {
                val marketList = apiService.getMarket()
            } catch (e: Exception) {
                errorMessage = e.toString()
            }

        }
    }

}