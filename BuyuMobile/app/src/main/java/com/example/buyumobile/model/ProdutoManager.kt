package com.example.buyumobile.model
import java.text.NumberFormat
import java.util.Locale

object ProdutoManager {
    val produtos: MutableList<Produtos> = mutableListOf()

    fun calcularTotal(): String {
        val total = produtos.sumOf { it.valorUnitario * 1 }
        return String.format(Locale.US, "%.2f", total)
    }
}