package com.estebanposada.sakeapp

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.estebanposada.sakeapp.ui.Constants
import com.estebanposada.sakeapp.ui.Screen
import com.estebanposada.sakeapp.ui.detail.DetailEvent
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.app_name)) }, navigationIcon = {
                if (currentRoute != Screen.HomeScreen.route) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_description)
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
                route = Screen.DetailScreen.route + Constants.ID_PARAM,
                arguments = listOf(navArgument(name = Constants.ID) {
                    type = NavType.IntType
                    defaultValue = -1
                })
            ) {
                val viewModel: DetailViewModel = hiltViewModel()
                DetailScreen(
                    state = viewModel.state.value,
                    onEvent = { event ->
                        when (event) {
                            is DetailEvent.OpenWebsite -> {
                                val intent = Intent(Intent.ACTION_VIEW, event.url.toUri())
                                context.startActivity(intent)
                            }

                            is DetailEvent.OpenMap -> {
                                val uri = Uri.parse(
                                    context.getString(
                                        R.string.geo_coordinates_address,
                                        event.latitude,
                                        event.longitude,
                                        Uri.encode(event.address)
                                    )
                                )
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                context.startActivity(intent)
                            }

                            is DetailEvent.ShowError -> viewModel.setError(event.message)
                        }
                    },
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