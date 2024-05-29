package com.example.buyumobile.network

import com.example.buyumobile.model.CadastroUsuario
import com.example.buyumobile.model.Endereco
import com.example.buyumobile.model.LoginUsuario
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUsuarios {
    @GET("clientes")
    fun getUsuarios(): Call<List<CadastroUsuario>>

    @POST("clientes")
    fun postUsuario(@Body cadastroUsuario:CadastroUsuario): Call<CadastroUsuario>

    @POST("clientes/login")
    fun loginUsuario(@Body loginUsuario: LoginUsuario): Call<Usuario>

    @PUT("clientes/{idUser}/enderecos")
    fun putEndereco(
        @Path("idUser") idUser: String,
        @Query("cep") cep: String,
        @Body editarEndereco: Endereco
    ): Call<Endereco>
}

