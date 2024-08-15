package com.camila.search.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.camila.search.data.StockData

class ProjectViewModel : ViewModel() {

    var selectedStock = mutableStateOf<StockData?>(null)

    var stocks = mutableListOf(
        StockData(id = "1", name = "Estoque A"),
        StockData(id = "2", name = "Estoque B"),
        StockData(id = "3", name = "Estoque C"),
        StockData(id = "4", name = "Estoque D"),
        StockData(id = "5", name = "Estoque E"),
        StockData(id = "6", name = "Estoque F"),
        StockData(id = "7", name = "Loja A"),
        StockData(id = "8", name = "Loja B"),
        StockData(id = "9", name = "Loja C"),
        StockData(id = "10", name = "Loja D"),
        StockData(id = "11", name = "Loja E")
    )
}