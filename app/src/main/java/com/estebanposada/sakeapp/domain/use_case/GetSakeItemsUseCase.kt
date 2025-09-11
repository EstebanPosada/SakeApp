package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.repository.SakeRepository

class GetSakeItemsUseCase(
    private val repository: SakeRepository
) {
    suspend operator fun invoke() = repository.getMockSakeItems()
}