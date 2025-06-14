package com.estebanposada.sakeapp.ui.home

import com.estebanposada.sakeapp.domain.model.Sake

data class HomeState(
    val items: List<Sake> = emptyList()
)
