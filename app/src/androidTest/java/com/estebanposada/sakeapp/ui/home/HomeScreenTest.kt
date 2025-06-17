package com.estebanposada.sakeapp.ui.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.estebanposada.sakeapp.di.TestAppModule
import com.estebanposada.sakeapp.domain.model.sakes
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(TestAppModule::class)
class HomeScreenUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun homeScreen_showItems() {
        val items = sakes
        composeRule.setContent {
            HomeScreen(
                state = HomeState(items = items, isLoading = false),
                onItemClick = {}
            )
        }
        composeRule.onNodeWithText("Sake 1").assertIsDisplayed()
        composeRule.onNodeWithText("Sake 2").assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsEmptyMessage() {
        composeRule.setContent {
            HomeScreen(
                state = HomeState(items = emptyList()),
                onItemClick = {}
            )
        }
        composeRule.onNodeWithText("No items found").assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsLoadingState() {
        composeRule.setContent {
            HomeScreen(state = HomeState(isLoading = true), onItemClick = {})
        }
        composeRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }
}