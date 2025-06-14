package com.estebanposada.sakeapp.domain.use_case

import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.domain.repository.SakeRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
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
        assertEquals(someSake, result)
    }
}