package com.camila.search.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.camila.search.data.OptionData
import com.camila.search.navigation.NavigationKeys.Companion.OPTION_KEY
import com.camila.search.navigation.NavigationKeys.Companion.SELECTED_OPTION_KEY
import com.camila.search.navigation.Routes

class ScreensViewModel(private val navController: NavController) : ViewModel() {

    var optionDataList: List<OptionData>? = emptyList()
    var selectedOptionId: String? = null
    var selectedStock = mutableStateOf<OptionData?>(null)

    fun filterByName(query: String): List<OptionData> {
        return optionsData.filter { opt ->
            opt.name.contains(query, ignoreCase = true)
        }
    }

    fun getParametersFromNav() {
        optionDataList =
            navController.previousBackStackEntry?.savedStateHandle?.get<List<OptionData>>(OPTION_KEY)

        selectedOptionId =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>(SELECTED_OPTION_KEY)

    }

    fun setParametersToNav() {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            OPTION_KEY,
            optionsData
        )
        navController.currentBackStackEntry?.savedStateHandle?.set(
            SELECTED_OPTION_KEY,
            selectedStock.value?.id ?: optionsData.firstOrNull()?.id
        )
    }

    fun goToSecondScreen() {
        navController.navigate(Routes.SECOND_SCREEN.name)
    }

    fun goToFirstScreen() {
        navController.popBackStack()
    }

    //mock
    var optionsData = mutableListOf(
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