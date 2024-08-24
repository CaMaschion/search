package com.camila.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.camila.search.data.OptionData
import com.camila.search.ui.component.CardComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    viewModel: ScreensViewModel
) {

    viewModel.getParametersFromNav()

    val selectedOption by remember {
        mutableStateOf(viewModel.optionDataList?.find { it.id == viewModel.selectedOptionId })
    }

    var searchQuery by remember {
        mutableStateOf("")
    }

    var filteredOptions by remember { mutableStateOf(viewModel.optionDataList) }
    filteredOptions =
        if (searchQuery.isEmpty()) viewModel.optionDataList else viewModel.filterByName(searchQuery)

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { viewModel.goToFirstScreen() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                title = {
                    Text("Selecione uma opção")
                })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { query ->
                        searchQuery = query
                    },
                    label = { Text("Pesquisar") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    filteredOptions?.forEach { opt ->
                        CardComponent(
                            option = opt,
                            isSelected = selectedOption?.id == opt.id,
                        ) {
                            goToFirstScreen(viewModel, opt)
                        }
                    }
                }
            }
        }
    )
}

private fun goToFirstScreen(
    viewModel: ScreensViewModel,
    opt: OptionData
) {
    viewModel.selectedStock.value = opt
    viewModel.goToFirstScreen()
}

@Preview(showBackground = true)
@Composable
fun StockSelectionScreenPreview() {
    val navController = rememberNavController()
    SelectionScreen(
        viewModel = ScreensViewModel(navController)
    )
}
