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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.camila.search.navigation.NavigationKeys.Companion.SELECTED_STOCKS_KEY
import com.camila.search.navigation.NavigationKeys.Companion.STOCKS_KEY
import com.camila.search.data.StockData
import com.camila.search.ui.component.CardComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    navController: NavController,
    viewModel: ProjectViewModel
) {
    val stocks =
        navController.previousBackStackEntry?.savedStateHandle?.get<List<StockData>>(STOCKS_KEY)
            ?: emptyList()

    val selectedStockId =
        navController.previousBackStackEntry?.savedStateHandle?.get<String>(SELECTED_STOCKS_KEY)

    var selectedStock by remember {
        mutableStateOf(stocks.find { it.id == selectedStockId })// Verifica se o estoque está selecionado
    }

    var searchQuery by remember {
        mutableStateOf("")// Inicializa a pesquisa
    }

    var filteredStocks by remember { mutableStateOf(stocks) }// Inicializa a lista de estoques filtrados

    fun filterByName(query: String): List<StockData> {// Função para filtrar os estoques pelo nome
        return stocks.filter { stock ->
            stock.name.contains(query, ignoreCase = true)
        }
    }

    filteredStocks = if (searchQuery.isEmpty()) stocks else filterByName(searchQuery)// Atualiza a lista de estoques filtrados

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                    filteredStocks.forEach { stock ->
                        CardComponent(
                            stock = stock,// Passa o estoque para o componente
                            isSelected = selectedStock?.id == stock.id, // Verifica se o estoque está selecionado
                        ) {
                            viewModel.selectedStock.value = stock // Atualiza o estoque selecionado
                            navController.popBackStack() // Fecha a tela atual
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StockSelectionScreenPreview() {
    val navController = rememberNavController()
    SelectionScreen(
        navController = navController,
        viewModel = ProjectViewModel()
    )
}
