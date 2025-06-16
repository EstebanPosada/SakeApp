package com.estebanposada.sakeapp.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.sakeapp.domain.use_case.GetSakeByIdUseCase
import com.estebanposada.sakeapp.ui.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSakeByIdUseCase: GetSakeByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(DetailState())
    var state: State<DetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.ID)?.let { sakeId ->
            if (sakeId != Constants.NO_ID) {
                viewModelScope.launch {
                    getSakeById(sakeId)
                }
            }
        }
    }

    private suspend fun getSakeById(id: Int) {
        getSakeByIdUseCase(id).let { sake ->
            if (sake != null) {
                _state.value = state.value.copy(sake = sake)
            }
        }
    }

    fun setError(message: String) {
        _state.value = state.value.copy(error = message)
    }
}