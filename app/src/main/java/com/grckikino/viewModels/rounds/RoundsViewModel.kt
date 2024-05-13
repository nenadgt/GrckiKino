package com.grckikino.viewModels.rounds

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.grckikino.MainViewModel
import com.grckikino.data.models.round.Round
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoundsViewModel() : MainViewModel() {

    private val _rounds = MutableStateFlow(ArrayList<Round>())
    val rounds = _rounds.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                retrofitApi.getRounds(1100).let {
                    if (it.isSuccessful && it.body() != null) {
                        it.body()?.let { responseBody ->
                            _rounds.value = responseBody
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("TAGTEST", "Error: ${e.message}")
            }
        }
    }
}