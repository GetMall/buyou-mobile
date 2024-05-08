package com.example.buyumobile.model

import java.util.UUID

data class Shopping(
    val id: UUID,
    val nome: String,
    val lojas: List<Loja>
)
