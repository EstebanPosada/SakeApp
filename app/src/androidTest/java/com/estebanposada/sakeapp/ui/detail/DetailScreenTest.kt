package com.estebanposada.sakeapp.ui.detail

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
class DetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun detailScreen_showsSakeDetails() {
        val sake = sakes.first()
        composeRule.setContent {
            DetailScreen(state = DetailState(sake.toUiModel(), isLoading = false))
        }
        composeRule.onNodeWithText("Sake 1").assertIsDisplayed()
        composeRule.onNodeWithText("Description of Sake 1").assertIsDisplayed()
        composeRule.onNodeWithText("123 Sake St, Tokyo, Japan").assertIsDisplayed()
        composeRule.onNodeWithText("Visit Website").assertIsDisplayed()
    }

    @Test
    fun detailScreen_showsLoadingState() {
        composeRule.setContent {
            DetailScreen(state = DetailState(isLoading = true))
        }
        composeRule.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }

    @Test
    fun detailScreen_showsErrorMessage() {
        val errorMessage = "Error"
        composeRule.setContent {
            DetailScreen(
                state = DetailState(
                    isLoading = false,
                    error = DetailError.CustomMessage(errorMessage)
                )
            )
        }
        composeRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }
}