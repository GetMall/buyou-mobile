package com.example.buyumobile.network

import retrofit2.http.GET
import com.example.buyumobile.model.Produtos
import retrofit2.Call

interface ApiProdutos {
    @GET("produtos/{id}")
    fun getProdutos(): Call<List<Produtos>>
}