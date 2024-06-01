package com.example.buyumobile.model

import java.util.UUID

data class EnderecoDetalhado(
    val id: UUID?,
    val cep: String,
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val numeroCasa: String?,
    val complemento: String?,
    val latitude: Double?,
    val longitude: Double?
)
