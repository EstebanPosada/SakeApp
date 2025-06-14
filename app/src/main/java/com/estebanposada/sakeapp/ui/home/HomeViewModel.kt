package com.estebanposada.sakeapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    var state: State<HomeState> = _state
}