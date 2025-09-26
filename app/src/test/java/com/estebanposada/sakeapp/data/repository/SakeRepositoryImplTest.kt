package com.estebanposada.sakeapp.data.repository

import com.estebanposada.sakeapp.data.data_source.SakeDao
import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.mock.Sake
import com.estebanposada.sakeapp.mock.SakeApiService
import com.estebanposada.sakeapp.mock.toDomainSake
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
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
    fun `When getSakeItems is called, then return successful`() = runTest {
        // Given
        val expectedSakeItems = sakes

        // When
        coEvery { dao.getAllSakeItems() } returns listOf(expectedSakeItems).asFlow()
        val result = repository.getSakeItems().first()

        // Then
        assertThat(result).isEqualTo(expectedSakeItems)
    }

    @Test
    fun `When getSakeById is called, then return successful`() = runTest {
        // Given
        val id = 1
        val expectedSakeItem = sakes.first()

        // When
        coEvery { dao.getSakeById(id) } returns expectedSakeItem
        val result = repository.getSakeById(id)

        // Then
        assertThat(result).isEqualTo(expectedSakeItem)
    }

    @Test
    fun `When getMockSakeItems is called, then return successful`() = runTest {
        val mockList = listOf(
            Sake(
                "name",
                "Sake 1",
                null,
                4.0,
                "address",
                listOf(1.0, 1.0),
                "mapLink",
                "website"
            ),
        )
        val domainData = mockList.map { it.toDomainSake() }

        coEvery { api.getMockSakeList() } returns mockList
        coEvery { dao.insertAll(any()) } just runs
        coEvery { dao.getAllSakeItems() } returns flowOf(domainData)

        val result = repository.getMockSakeItems()

        assertThat(result.first()).isEqualTo(domainData)
        coEvery { api.getMockSakeList() }
        coEvery { dao.insertAll(domainData) }
        coEvery { dao.getAllSakeItems() }

    }
}