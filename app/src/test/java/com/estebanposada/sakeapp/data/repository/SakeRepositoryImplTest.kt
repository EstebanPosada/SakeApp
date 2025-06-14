package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.domain.model.sakes
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SakeRepositoryImplTest {
    private lateinit var repository: SakeRepositoryImpl
    private lateinit var dao: SakeDao

    @Before
    fun setUp() {
        dao = mockk()
        repository = SakeRepositoryImpl(dao)
    }

    @Test
    fun `When getSakeItems is called, then return successful`() = runBlocking {
        // Given
        val expectedSakeItems = sakes

        // When
        coEvery { dao.getAllSakeItems() } returns listOf(expectedSakeItems).asFlow()
        val result = repository.getSakeItems().first()

        // Then
        assertEquals(expectedSakeItems, result)
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
        assertEquals(expectedSakeItem, result)
    }
}