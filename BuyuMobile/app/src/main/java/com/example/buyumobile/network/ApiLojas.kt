package com.example.buyumobile.network

import com.example.buyumobile.model.Loja
import com.example.buyumobile.model.Produtos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiLojas {

    @GET("lojas/{id}")
    fun getLoja(@Path("id") id: String): Call<Loja>
}