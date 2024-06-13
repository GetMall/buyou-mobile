package com.example.buyumobile.network

import com.example.buyumobile.model.Pedidos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPedidos {
    @GET("pedidos/por-cliente/{id}")
    fun getPedidos(
        @Path("id") id: String
    ): Call<List<Pedidos>>
}