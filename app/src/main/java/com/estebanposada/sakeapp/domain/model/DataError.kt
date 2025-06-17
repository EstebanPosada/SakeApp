package com.estebanposada.sakeapp.domain.model

sealed class DataError {
    object NetworkError : DataError()
    object NotFound : DataError()
    data class UnknownError(val message: String) : DataError()
}