package com.example.studyplanner

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.ui.assignment.AssignmentDetailsScreen
import com.example.studyplanner.ui.assignment.AssignmentEntryScreen
import com.example.studyplanner.ui.assignment.AssignmentList
import com.example.studyplanner.ui.focus.FocusScreen
import com.example.studyplanner.ui.home.DashboardScreen
import com.example.studyplanner.ui.module.ModulesScreen

enum class StudyPlannerScreen(@StringRes val title: Int) {
    Dashboard(title = R.string.dashboard),
    Assignments(title = R.string.assignments),
    AssignmentDetails(title = R.string.assignment_details),
    AssignmentEntry(title = R.string.add_assignment),
    Modules(title = R.string.modules),
    Focus(title = R.string.focus_session)
}

@Composable
fun StudyPlannerApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = StudyPlannerScreen.Dashboard.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = StudyPlannerScreen.Dashboard.name) {
                DashboardScreen(
                    assignments = Datasource().loadAssignments(),
                    modules = Datasource().loadModules(),
                    focusMinutes = 275,
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = StudyPlannerScreen.Assignments.name) {
                AssignmentList(
                    assignments = Datasource().loadAssignments(),
                    modules = Datasource().loadModules(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = StudyPlannerScreen.AssignmentDetails.name) {
                AssignmentDetailsScreen(
                    assignment = Datasource().loadAssignments()[5],
                    moduleName = "Mobile App Development",
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = StudyPlannerScreen.AssignmentEntry.name) {
                AssignmentEntryScreen(modifier = Modifier.fillMaxSize())
            }
            composable(route = StudyPlannerScreen.Modules.name) {
                ModulesScreen(
                    modules = Datasource().loadModules(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = StudyPlannerScreen.Focus.name) {
                FocusScreen(
                    assignmentTitle = "Mobile App Project",
                    timerText = "25:00",
                    statusText = stringResource(R.string.place_phone_face_down),
                    distractions = 0,
                    lightLevel = 320,
                    showLightWarning = false,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}