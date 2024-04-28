package com.example.buyumobile.component

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Pedidos : Screens("pedidos")
}