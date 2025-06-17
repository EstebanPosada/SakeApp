package com.estebanposada.sakeapp.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.sakeapp.domain.model.Result
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
                _state.value = state.value.copy(isLoading = true)
                viewModelScope.launch {
                    getSakeById(sakeId)
                }
            }
        }
    }

    private suspend fun getSakeById(id: Int) {
        when (val result = getSakeByIdUseCase(id)) {
            is Result.Success -> {
                _state.value = state.value.copy(sake = result.data.toUiModel(), isLoading = false)
            }

            is Result.Failure -> {
                _state.value =
                    state.value.copy(error = result.error.toDetailError(), isLoading = false)
            }
        }
    }

    fun setError(error: DetailError) {
        _state.value = state.value.copy(error = error)
    }
}