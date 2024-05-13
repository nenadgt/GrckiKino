package com.grckikino.viewModels.round

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.grckikino.MainViewModel
import com.grckikino.data.models.round.Round
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class RoundViewModel: MainViewModel() {

    private val _round = MutableStateFlow(Round())
    val round = _round.asStateFlow()

    private val _selectedNumbers = mutableStateListOf<Int>()
    val selectedNumbers: List<Int> = _selectedNumbers

    val totalBoxes = 80 // Centralizing this value

    // New state for the maximum number of selections
    var maxSelections = mutableIntStateOf(8) // Default or retrieve this value from settings or database

    fun selectNumber(number: Int) {
        if (_selectedNumbers.contains(number)) {
            _selectedNumbers.remove(number)
        } else if (_selectedNumbers.size < maxSelections.intValue) {
            _selectedNumbers.add(number)
        }
    }

    // Function to select a random set of numbers
    fun randomSelectNumbers() {
        _selectedNumbers.clear()
        val range = 1..totalBoxes // Use totalBoxes as the upper limit

        // Generate a distinct list of random numbers
        val newSelections = mutableSetOf<Int>()
        while (newSelections.size < maxSelections.intValue) {
            newSelections.add(Random.nextInt(range.first, range.last))
        }

        _selectedNumbers.addAll(newSelections)

        // Ensure the list is exactly maxSelections long
        if (_selectedNumbers.size > maxSelections.intValue) {
            _selectedNumbers.shuffle()
            _selectedNumbers.subList(0, maxSelections.intValue).let {
                _selectedNumbers.clear()
                _selectedNumbers.addAll(it)
            }
        }
    }

    // Function to change the maximum number of selections
    fun setMaxSelections(max: Int) {
        maxSelections.intValue = max
    }

    fun getRound(drawId: Int) {
        viewModelScope.launch {
            try {
                retrofitApi.getRoundById(1100, drawId).let {
                    if (it.isSuccessful && it.body() != null) {
                        it.body()?.let { responseBody ->
                            _round.value = responseBody
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("TAGTEST", "Error: ${e.message}")
            }
        }
    }
}