package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.mock.SakeApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SakeRepositoryImplTest {
    private lateinit var repository: SakeRepositoryImpl
    private lateinit var dao: SakeDao
    private lateinit var api: SakeApiService

    @Before
    fun setUp() {
        dao = mockk()
        api = mockk()
        repository = SakeRepositoryImpl(dao, api)
    }

    @Test
    fun `When getSakeItems is called, then return successful`() = runBlocking {
        // Given
        val expectedSakeItems = sakes

        // When
        coEvery { dao.getAllSakeItems() } returns listOf(expectedSakeItems).asFlow()
        val result = repository.getSakeItems().first()

        // Then
        assertThat(result).isEqualTo(expectedSakeItems)
    }

    @Test
    fun `When getSakeById is called, then return successful`() = runBlocking {
        // Given
        val id = 1
        val expectedSakeItem = sakes.first()

        // When
        coEvery { dao.getSakeById(id) } returns expectedSakeItem
        val result = repository.getSakeById(id)

        // Then
        assertThat(result).isEqualTo(expectedSakeItem)
    }
}