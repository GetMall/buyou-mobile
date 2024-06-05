package com.example.buyumobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val BASE_URL = "http://3.88.3.254:8080/api/"

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

    fun getApiPedidos(): ApiPedidos {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPedidos::class.java)

        return cliente
    }

    fun getApiPagamento(): ApiPagamento {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiPagamento::class.java)

        return cliente
    }

}