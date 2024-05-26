package com.example.buyumobile.model

import java.util.UUID

data class Loja(
    val id:UUID? = null,
    val nome:String,
    val imagens:List<Imagem>,
)
