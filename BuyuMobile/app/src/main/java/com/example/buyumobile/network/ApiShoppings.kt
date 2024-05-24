package com.example.buyumobile.network

import com.example.buyumobile.model.Shopping
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiShoppings {
    @GET("shopping")
    fun getShoppings(): Call<List<Shopping>>

    @GET("shopping/proximos/{id}")
    fun getShoppingsProximos(@Path("id") userId: String): Call<List<Shopping>>

}