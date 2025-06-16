package com.estebanposada.sakeapp.ui.home

import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getSakeItemsUseCase: GetSakeItemsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getSakeItemsUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state is empty`() {
        coEvery { getSakeItemsUseCase() } returns flowOf(emptyList())
        viewModel = HomeViewModel(getSakeItemsUseCase)
        assertThat(viewModel.state.value.items).isEmpty()
    }

    @Test
    fun `init should call getItems and update state`() = runTest {
        // Given
        val sakeList = sakes

        // When
        coEvery { getSakeItemsUseCase() } returns flowOf(sakeList)
        viewModel = HomeViewModel(getSakeItemsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.items).isEqualTo(sakeList)
        coVerify(exactly = 1) { getSakeItemsUseCase() }
    }

}