package com.camila.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.camila.search.navigation.NavigationKeys.Companion.SELECTED_OPTION_KEY
import com.camila.search.navigation.NavigationKeys.Companion.OPTION_KEY
import com.camila.search.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(
    viewModel: ScreensViewModel
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Escolha uma opção:") }) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = { goToSecondScreen(viewModel) }
                ) {
                    Text(text = viewModel.selectedStock.value?.name ?: "Selecione uma opção")
                }
            }
        })
}

private fun goToSecondScreen(
    viewModel: ScreensViewModel,
) {
    viewModel.setParametersToNav()
    viewModel.goToSecondScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewCardListScreen() {
    val navController = rememberNavController()
    FirstScreen(
        viewModel = ScreensViewModel(navController)
    )
}
