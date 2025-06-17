package com.estebanposada.sakeapp.domain.model

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: DataError) : Result<Nothing>()
}