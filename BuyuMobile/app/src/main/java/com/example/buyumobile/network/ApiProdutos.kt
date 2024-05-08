package com.example.buyumobile.network

import com.example.buyumobile.model.Produto
import retrofit2.Call
import retrofit2.http.GET

interface ApiProdutos {
    @GET("/produto")
    fun getProdutos(): Call<List<Produto>>

    @GET("/produto/{id}")
    fun getProdutoById(id: Int): Call<Produto>
}