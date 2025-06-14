package com.estebanposada.sakeapp.ui

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object DetailScreen : Screen("detail")
}