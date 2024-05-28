package com.example.buyumobile.model

import java.util.UUID

data class Produtos(
    val id: UUID,
    val nome: String,
    val valorUnitario: Double,
    val descricao: String,
    val imagens:List<Imagem>
)
