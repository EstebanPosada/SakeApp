package com.estebanposada.sakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.estebanposada.sakeapp.ui.Screen
import com.estebanposada.sakeapp.ui.detail.DetailScreen
import com.estebanposada.sakeapp.ui.detail.DetailViewModel
import com.estebanposada.sakeapp.ui.home.HomeScreen
import com.estebanposada.sakeapp.ui.home.HomeViewModel
import com.estebanposada.sakeapp.ui.theme.SakeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SakeAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.HomeScreen.route, modifier = modifier) {
        composable(Screen.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                modifier = modifier,
                state = viewModel.state.value,
                onItemClick = { id -> navController.navigate(Screen.DetailScreen.route) }
            )
        }
        composable(Screen.DetailScreen.route) {
            val viewModel: DetailViewModel = hiltViewModel()
            DetailScreen(
                state = viewModel.state.value,
            )
        }
    }
}