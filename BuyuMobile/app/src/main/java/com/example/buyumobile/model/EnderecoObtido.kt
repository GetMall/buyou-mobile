package com.example.buyumobile.model

import java.util.UUID

data class EnderecoObtido(
    val id: UUID,
    val nome: String,
    val cpf: String?,
    val email: String,
    val celular: String?,
    val dataNascimento: String?,
    val genero: String?,
    val endereco: EnderecoDetalhado?
)
