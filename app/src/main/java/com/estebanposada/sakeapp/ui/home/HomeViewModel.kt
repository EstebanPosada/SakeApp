package com.estebanposada.sakeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import com.estebanposada.sakeapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSakeItemsUseCase: GetSakeItemsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state get() = _state

    private val _effect: MutableSharedFlow<MainEffect> = MutableSharedFlow()
    val effect get() = _effect


    init {
        handleIntent(MainIntent.FetchSakes)
    }

    fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.FetchSakes -> getItems()
            is MainIntent.ItemClicked -> handleItemClicked(intent.sakeId)
        }

    }

    private fun handleItemClicked(id: Int) {
        viewModelScope.launch {
            _effect.emit(MainEffect.NavigateToDetail(id))
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            try {
                getSakeItemsUseCase().collect { result ->
                    when (result) {
                        is Resource.Success<List<Sake>> -> {
                            result.data?.let {
                                _state.value = MainState.LoadedSakes(it)
                            }
                        }

                        is Resource.Error<List<Sake>> -> {
                            _state.value = MainState.Error(result.message)
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = MainState.Error(e.message)
            }
        }
    }
}