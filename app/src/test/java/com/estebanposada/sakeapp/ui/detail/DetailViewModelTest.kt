package com.estebanposada.sakeapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.estebanposada.sakeapp.domain.model.DataError
import com.estebanposada.sakeapp.domain.model.Result
import com.estebanposada.sakeapp.domain.model.sakes
import com.estebanposada.sakeapp.domain.use_case.GetSakeByIdUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getSakeByIdUseCase: GetSakeByIdUseCase
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getSakeByIdUseCase = mockk()
    }

    @After
    fun tierDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state is empty`() {
        val savedStateHandle = mockk<SavedStateHandle>() {
            every { get<Int>("id") } returns -1
        }
        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)
        assertThat(viewModel.state.value.sake).isNull()
    }

    @Test
    fun `init should call getSakeById and update state`() = runTest {
        // Given
        val sake = sakes.first()
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns sake.id
        }

        // When
        coEvery { sake.id?.let { getSakeByIdUseCase(it) } } returns Result.Success(sake)

        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.sake).isEqualTo(sake.toUiModel())
    }

    @Test
    fun `setError should update error in state with CustomMessage`() {
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns null
        }
        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)

        val error = DetailError.CustomMessage("Error")
        viewModel.setError(error)

        assertThat(viewModel.state.value.error).isEqualTo(error)
    }

    @Test
    fun `init should call getSakeById, when sake is null, do nothing`() = runTest {
        // Given
        val sake = sakes.first()
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns sake.id
        }

        // When
        coEvery { sake.id?.let { getSakeByIdUseCase(it) } } returns Result.Failure(DataError.NetworkError)

        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertThat(viewModel.state.value.sake).isNull()
    }

    @Test
    fun `init should not call getSakeById if id is null`() = runTest {
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns null
        }

        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)
        assertThat(viewModel.state.value.sake).isNull()
    }

    @Test
    fun `setError should update error in state`() {
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns null
        }
        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)

        val error = DetailError.CustomMessage("Error")
        viewModel.setError(error)

        assertThat(viewModel.state.value.error).isEqualTo(error)
    }

    @Test
    fun `setError should overwrite previous error`() {
        val savedStateHandle = mockk<SavedStateHandle> {
            every { get<Int>("id") } returns null
        }
        viewModel = DetailViewModel(getSakeByIdUseCase, savedStateHandle)

        val firstError = DetailError.CustomMessage("First")
        val secondError = DetailError.CustomMessage("Second")
        viewModel.setError(firstError)
        viewModel.setError(secondError)

        assertThat(viewModel.state.value.error).isEqualTo(secondError)
    }
}