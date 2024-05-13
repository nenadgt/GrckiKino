package com.grckikino.viewModels.round

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue

class QuotesViewModel : ViewModel() {
    private val _quotePairs = mutableStateOf(
        listOf(
            "B.K" to "quota",
            "1" to "3.75",
            "2" to "14",
            "3" to "65",
            "4" to "275",
            "5" to "1 350",
            "6" to "6 500",
            "7" to "25 000"
        )
    )
    val quotePairs by _quotePairs
}