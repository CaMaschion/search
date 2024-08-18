package com.camila.search.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.camila.search.data.OptionData

class ScreensViewModel : ViewModel() {

    var selectedStock = mutableStateOf<OptionData?>(null)

    var stocks = mutableListOf(
        OptionData(id = "1", name = "Opção 1"),
        OptionData(id = "2", name = "Opção 2"),
        OptionData(id = "3", name = "Opção 3"),
        OptionData(id = "4", name = "Opção 4"),
        OptionData(id = "5", name = "Opção 5"),
        OptionData(id = "6", name = "Opção 6"),
        OptionData(id = "7", name = "Escolha A"),
        OptionData(id = "8", name = "Escolha B"),
        OptionData(id = "9", name = "Escolha C"),
        OptionData(id = "10", name = "Escolha D"),
        OptionData(id = "11", name = "Escolha E")
    )
}