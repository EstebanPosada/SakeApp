package com.estebanposada.sakeapp.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.estebanposada.sakeapp.domain.model.Sake

class DetailViewModel: ViewModel(){
     private val _state = mutableStateOf(DetailState())
     var state: State<DetailState> = _state

}