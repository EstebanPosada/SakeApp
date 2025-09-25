package com.estebanposada.sakeapp.ui.home

import com.estebanposada.sakeapp.domain.model.Sake

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadedSakes(val sakeList: List<Sake>): MainState()
    data class Error(val error: String?): MainState()
}