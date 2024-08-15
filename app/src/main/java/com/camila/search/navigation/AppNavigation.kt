package com.camila.search.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.camila.search.data.StockData
import com.camila.search.ui.FirstScreen
import com.camila.search.ui.ProjectViewModel
import com.camila.search.ui.SelectionScreen

class NavigationKeys {
    companion object {
        const val STOCKS_KEY = "stocks"
        const val SELECTED_STOCKS_KEY = "selectedStocks"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = ProjectViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.CARD_LIST_SCREEN.name,
        modifier = Modifier.padding(16.dp)
    ) {
        composable(Routes.CARD_LIST_SCREEN.name) {
            FirstScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Routes.STOCK_SELECTION_SCREEN.name) {
            SelectionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
