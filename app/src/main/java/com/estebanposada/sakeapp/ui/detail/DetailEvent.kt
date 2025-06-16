package com.estebanposada.sakeapp.ui.detail

sealed class DetailEvent {
    data class OpenMap(val latitude: Double, val longitude: Double, val address: String) :
        DetailEvent()
    data class OpenWebsite(val url: String) : DetailEvent()
    data class ShowError(val message: String) : DetailEvent()
}