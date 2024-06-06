package com.example.buyumobile.model

import java.util.UUID

data class Loja(
    val id: UUID,
    val nome: String,
    val produtos: List<Produtos>,
    val imagens:List<Imagem>
)
