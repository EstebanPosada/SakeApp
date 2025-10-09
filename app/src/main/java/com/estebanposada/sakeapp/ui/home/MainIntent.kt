package com.estebanposada.sakeapp.ui.home

sealed class MainIntent {
    object FetchSakes: MainIntent()
    data class ItemClicked(val sakeId: Int): MainIntent()
}
sealed class MainEffect{
    data class NavigateToDetail(val sakeId: Int): MainEffect()
}