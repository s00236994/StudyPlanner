package com.example.studyplanner.ui.assignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AssignmentEntryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AssignmentEntryUiState())
    val uiState: StateFlow<AssignmentEntryUiState> = _uiState.asStateFlow()

    var title by mutableStateOf("")
        private set

    var notes by mutableStateOf("")
        private set

    fun updateTitle(newTitle: String) {
        title = newTitle
    }

    fun updateNotes(newNotes: String) {
        notes = newNotes
    }

    fun saveAssignment(): Boolean {
        val isValid = title.isNotBlank()
        _uiState.update { currentState ->
            currentState.copy(isTitleError = !isValid)
        }
        return isValid
    }
}