package com.example.buyumobile.network

import com.example.buyumobile.model.MyGlobals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL = "http://${MyGlobals.ipFixo}:8080/api/"

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

    fun getApiLojas(): ApiLojas {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiLojas::class.java)

        return cliente
    }

    fun getApiProdutos(): ApiProdutos {
        val cliente = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiProdutos::class.java)

        return cliente
    }
}