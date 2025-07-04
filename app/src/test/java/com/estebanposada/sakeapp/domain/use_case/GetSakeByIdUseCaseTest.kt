package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.model.DataError
import com.estebanposada.sakeapp.domain.model.Result
import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSakeByIdUseCaseTest {
    private lateinit var getSakeByIdUseCase: GetSakeByIdUseCase
    private lateinit var repository: SakeRepository

    @Before
    fun setUp() {
        repository = mockk()
        getSakeByIdUseCase = GetSakeByIdUseCase(repository)
    }

    @Test
    fun `When getSakeById is called, then return successful`() = runBlocking {
        // Given
        val someSake = sakes.first()
        val id = someSake.id ?: 1

        // When
        coEvery { repository.getSakeById(any()) } returns someSake
        val result = getSakeByIdUseCase(id)

        // Then
        assertThat(result).isEqualTo(Result.Success(someSake))
    }

    @Test
    fun `When getSakeById returns null, then return Failure NotFound`() = runBlocking {
        // Given
        val id = 99
        coEvery { repository.getSakeById(id) } returns null

        // When
        val result = getSakeByIdUseCase(id)

        // Then
        assertThat(result).isEqualTo(Result.Failure(DataError.NotFound))
    }

    @Test
    fun `When getSakeById throws exception, then return Failure UnknownError`() = runBlocking {
        // Given
        val id = 1
        val errorMessage = "DB error"
        coEvery { repository.getSakeById(id) } throws RuntimeException(errorMessage)

        // When
        val result = getSakeByIdUseCase(id)

        // Then
        assertThat(result).isEqualTo(
            Result.Failure(DataError.UnknownError(errorMessage))
        )
    }
}