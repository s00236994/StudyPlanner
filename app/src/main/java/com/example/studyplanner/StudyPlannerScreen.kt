package com.example.studyplanner

import androidx.annotation.StringRes

enum class StudyPlannerScreen(@StringRes val title: Int) {
    Dashboard(title = R.string.dashboard),
    Assignments(title = R.string.assignments),
    AssignmentDetails(title = R.string.assignment_details),
    AssignmentEntry(title = R.string.add_assignment),
    Modules(title = R.string.modules),
    Focus(title = R.string.focus_session)
}