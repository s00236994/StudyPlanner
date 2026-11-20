package com.example.studyplanner.ui.focus

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FocusViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FocusUiState())
    val uiState: StateFlow<FocusUiState> = _uiState.asStateFlow()

    fun startTimer() {
        if (_uiState.value.secondsLeft > 0) {
            _uiState.update { it.copy(isRunning = true) }
        }
    }

    fun pauseTimer() {
        _uiState.update { it.copy(isRunning = false) }
    }

    fun tick() {
        _uiState.update { currentState ->
            if (!currentState.isRunning) {
                currentState
            } else if (currentState.secondsLeft <= 1) {
                currentState.copy(secondsLeft = 0, isRunning = false)
            } else {
                currentState.copy(secondsLeft = currentState.secondsLeft - 1)
            }
        }
    }
}