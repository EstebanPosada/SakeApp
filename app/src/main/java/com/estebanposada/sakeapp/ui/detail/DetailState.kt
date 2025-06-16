package com.estebanposada.sakeapp.ui.detail

import com.estebanposada.sakeapp.domain.model.Sake

data class DetailState(
    val sake: Sake? = null,
    val error: String? = null,
)
