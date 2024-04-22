package com.example.buyumobile.network

import com.example.buyumobile.model.CadastroUsuario
import com.example.buyumobile.model.LoginUsuario
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUsuarios {
    @GET("/clientes")
    fun getUsuarios(): Call<List<CadastroUsuario>>

    @POST("/clientes")
    fun postUsuario(@Body cadastroUsuario:CadastroUsuario): Call<CadastroUsuario>

    @POST("/clientes/login")
    fun loginUsuario(@Body loginUsuario: LoginUsuario): Call<LoginUsuario>
}

