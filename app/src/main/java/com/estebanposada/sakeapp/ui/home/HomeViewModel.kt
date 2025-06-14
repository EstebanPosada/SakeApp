package com.estebanposada.sakeapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSakeItemsUseCase: GetSakeItemsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    var state: State<HomeState> = _state

    init {
        getItems()
    }

    fun getItems() {
        viewModelScope.launch {
            getSakeItemsUseCase().collect { sakeItems ->
                _state.value = _state.value.copy(items = sakeItems)
            }
        }
    }
}