package com.example.studyplanner.ui.focus

data class FocusUiState(
    val secondsLeft: Int = 25 * 60,
    val isRunning: Boolean = false,
    val distractions: Int = 0
)
