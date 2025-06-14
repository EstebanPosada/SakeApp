package com.estebanposada.sakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sake App") }, navigationIcon = {
                if (currentRoute != Screen.HomeScreen.route) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            })
        },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.HomeScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    modifier = modifier,
                    state = viewModel.state.value,
                    onItemClick = { id -> navController.navigate(Screen.DetailScreen.route + "/$id") }
                )
            }
            composable(
                route = Screen.DetailScreen.route + "/{id}",
                arguments = listOf(navArgument(name = "id") {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) {
                val viewModel: DetailViewModel = hiltViewModel()
                DetailScreen(
                    state = viewModel.state.value,
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    Navigation()
}