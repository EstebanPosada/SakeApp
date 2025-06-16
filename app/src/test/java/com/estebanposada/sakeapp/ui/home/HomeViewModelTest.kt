package com.estebanposada.sakeapp.ui.home

import com.estebanposada.sakeapp.domain.use_case.GetSakeItemsUseCase
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var getSakeItemsUseCase: GetSakeItemsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        getSakeItemsUseCase = mockk()
        viewModel = HomeViewModel(getSakeItemsUseCase)
    }

    /*@Test
    fun `test initial state is empty`() {
        assert(viewModel.state.value.items.isEmpty())
    }

    @Test
    fun `test getItems populates state with sake items`() {
        // Given

        // When
    }*/

}