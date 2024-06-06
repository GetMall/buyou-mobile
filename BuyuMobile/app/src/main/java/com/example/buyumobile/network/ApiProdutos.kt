package com.example.buyumobile.network

import retrofit2.http.GET
import com.example.buyumobile.model.Produtos
import retrofit2.Call
import retrofit2.http.Path

interface ApiProdutos {
    @GET("produtos/{id}")
    fun getProdutos(): Call<List<Produtos>>

    @GET("produtos/{id}")
    fun getProduto(@Path("id") id: String): Call<Produtos>
}