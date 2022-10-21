package com.example.shopcompose.network

import com.example.shopcompose.models.DataModelElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface Apiinterface {
    @GET("3e99011b-bcc7-4a16-a6de-722ee54a5b42")
    suspend fun getMarket(): List<DataModelElement>

    companion object {
        var apiService: Apiinterface? = null
        fun getInstance() : Apiinterface {
            if (apiService == null) {
                apiService = Retrofit.Builder().baseUrl("https://mocki.io/v1/").addConverterFactory(GsonConverterFactory.create()).build().create(Apiinterface::class.java)
            }
            return apiService!!
        }
    }
}