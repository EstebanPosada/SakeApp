package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.repository.SakeRepository

class GetSakeByIdUseCase(
    private val repository: SakeRepository
) {
    suspend operator fun invoke(id: Int) = repository.getSakeById(id)
}