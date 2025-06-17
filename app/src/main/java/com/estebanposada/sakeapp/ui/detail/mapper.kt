package com.estebanposada.sakeapp.ui.detail

import com.estebanposada.sakeapp.R
import com.estebanposada.sakeapp.domain.model.DataError
import com.estebanposada.sakeapp.domain.model.Sake

fun Sake.toUiModel() = SakeUiModel(
    id = id ?: -1,
    name = name,
    description = description,
    imageUrl = picture,
    rating = rating,
    address = address,
    website = website,
    coordinates = if (coordinates.size == 2) Pair(coordinates[0], coordinates[1]) else null
)

fun DataError.toDetailError(): DetailError {
    return when (this) {
        DataError.NetworkError -> DetailError.Custom(R.string.network_error)
        DataError.NotFound -> DetailError.Custom(R.string.not_found)
        is DataError.UnknownError -> DetailError.CustomMessage(message)
    }
}