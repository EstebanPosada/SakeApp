package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSakeItemsUseCaseTest {
    private lateinit var getSakeItemsUseCase: GetSakeItemsUseCase
    private lateinit var repository: SakeRepository

    @Before
    fun setUp() {
        repository = mockk()
        getSakeItemsUseCase = GetSakeItemsUseCase(repository)
    }

    @Test
    fun `When getSakeItems is called, then return successful`() = runBlocking {
        // Given
        val someSakes = sakes

        // When
        coEvery { repository.getMockSakeItems() } returns listOf(someSakes).asFlow()
        val result = getSakeItemsUseCase().first()

        // Then
        assertThat(result).isEqualTo(someSakes)
    }
}