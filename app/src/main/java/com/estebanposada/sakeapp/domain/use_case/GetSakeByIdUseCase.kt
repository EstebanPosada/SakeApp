package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.model.DataError
import com.estebanposada.sakeapp.domain.model.Result
import com.estebanposada.sakeapp.domain.model.Sake
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.estebanposada.sakeapp.ui.Constants

class GetSakeByIdUseCase(
    private val repository: SakeRepository
) {
    suspend operator fun invoke(id: Int): Result<Sake> =
        try {
            val sake = repository.getSakeById(id)
            if (sake != null) {
                Result.Success(sake)
            } else {
                Result.Failure(DataError.NotFound)
            }
        } catch (e: Exception) {
            Result.Failure(DataError.UnknownError(e.message ?: Constants.UNKNOWN_ERROR))
        }
}