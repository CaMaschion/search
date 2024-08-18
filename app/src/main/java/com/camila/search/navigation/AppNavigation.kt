package com.camila.search.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camila.search.ui.FirstScreen
import com.camila.search.ui.ScreensViewModel
import com.camila.search.ui.SelectionScreen

class NavigationKeys {
    companion object {
        const val OPTION_KEY = "stocks"
        const val SELECTED_OPTION_KEY = "selectedStocks"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = ScreensViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.FIRST_SCREEN.name,
        modifier = Modifier.padding(16.dp)
    ) {
        composable(Routes.FIRST_SCREEN.name) {
            FirstScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Routes.SECOND_SCREEN.name) {
            SelectionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
