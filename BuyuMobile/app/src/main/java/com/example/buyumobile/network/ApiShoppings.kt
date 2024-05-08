package com.example.buyumobile.network

import com.example.buyumobile.model.Shopping
import retrofit2.Call
import retrofit2.http.GET

interface ApiShoppings {
    @GET("shopping")
    fun getShoppings(): Call<List<Shopping>>
}