package com.estebanposada.sakeapp.ui.detail

data class DetailState(
    val sake: SakeUiModel? = null,
    val error: DetailError? = null,
)

sealed class DetailError {
    object AddressUnavailable : DetailError()
    object WebsiteUnavailable : DetailError()
    data class CustomMessage(val message: String) : DetailError()
    data class Custom(val messageResId: Int) : DetailError()
}

data class SakeUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val coordinates: Pair<Double, Double>? = null,
    val rating: Float,
    val address: String?,
    val website: String?
)