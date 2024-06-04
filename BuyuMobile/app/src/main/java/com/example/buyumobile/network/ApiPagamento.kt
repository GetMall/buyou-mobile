package com.example.buyumobile.network

import com.example.buyumobile.model.CadastroUsuario
import com.example.buyumobile.model.Pagamento
import com.example.buyumobile.model.PagamentoResultado
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPagamento {
    @POST("transacao/pix")
    fun postPagamento(@Body pagamento: Pagamento): Call<PagamentoResultado>
}