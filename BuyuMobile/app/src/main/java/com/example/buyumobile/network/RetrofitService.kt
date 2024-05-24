package com.example.buyumobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val BASE_URL = "http://100.27.232.35:8080/api/"

    fun getApiUsuarios(): ApiUsuarios {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiUsuarios::class.java)

        return cliente
    }

    fun getApiShoppings(): ApiShoppings {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiShoppings::class.java)

        return cliente
    }

}