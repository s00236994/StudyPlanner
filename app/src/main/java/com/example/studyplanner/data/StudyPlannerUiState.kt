package com.example.studyplanner.data

data class StudyPlannerUiState(
    val selectedAssignment: Assignment = Datasource().loadAssignments()[0]
)
