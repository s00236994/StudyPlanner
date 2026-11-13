package com.example.studyplanner.ui

import androidx.lifecycle.ViewModel
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.data.StudyPlannerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StudyPlannerViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(StudyPlannerUiState())
    val uiState: StateFlow<StudyPlannerUiState> = _uiState.asStateFlow()

    fun selectAssignment(assignment: Assignment) {
        _uiState.update { currentState ->
            currentState.copy(selectedAssignment = assignment)
        }
    }
}