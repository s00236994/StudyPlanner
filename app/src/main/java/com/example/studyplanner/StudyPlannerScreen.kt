package com.example.studyplanner

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.ui.StudyPlannerViewModel
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

private data class NavigationItemContent(
    val screen: StudyPlannerScreen,
    val icon: ImageVector,
    val text: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyPlannerAppBar(
    currentScreen: StudyPlannerScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
private fun StudyPlannerBottomNavigationBar(
    currentScreen: StudyPlannerScreen,
    onTabPressed: (StudyPlannerScreen) -> Unit,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentScreen == navItem.screen,
                onClick = { onTabPressed(navItem.screen) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                label = { Text(text = navItem.text) }
            )
        }
    }
}

@Composable
fun StudyPlannerApp(
    windowSize: WindowWidthSizeClass,
    viewModel: StudyPlannerViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = StudyPlannerScreen.valueOf(
        backStackEntry?.destination?.route ?: StudyPlannerScreen.Dashboard.name
    )

    val navigationItemContentList = listOf(
        NavigationItemContent(
            screen = StudyPlannerScreen.Dashboard,
            icon = Icons.Filled.Home,
            text = stringResource(R.string.dashboard)
        ),
        NavigationItemContent(
            screen = StudyPlannerScreen.Assignments,
            icon = Icons.Filled.DateRange,
            text = stringResource(R.string.assignments)
        ),
        NavigationItemContent(
            screen = StudyPlannerScreen.Modules,
            icon = Icons.AutoMirrored.Filled.List,
            text = stringResource(R.string.modules)
        ),
        NavigationItemContent(
            screen = StudyPlannerScreen.Focus,
            icon = Icons.Filled.PlayArrow,
            text = stringResource(R.string.focus)
        )
    )

    Scaffold(
        topBar = {
            StudyPlannerAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            StudyPlannerBottomNavigationBar(
                currentScreen = currentScreen,
                onTabPressed = { screen ->
                    navController.popBackStack(StudyPlannerScreen.Dashboard.name, inclusive = false)
                    if (screen != StudyPlannerScreen.Dashboard) {
                        navController.navigate(screen.name)
                    }
                },
                navigationItemContentList = navigationItemContentList
            )
        }
    ) { innerPadding ->
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
                    modifier = Modifier.fillMaxSize(),
                    onAssignmentClick = {
                        viewModel.selectAssignment(it)
                        navController.navigate(StudyPlannerScreen.AssignmentDetails.name)
                    }
                )
            }
            composable(route = StudyPlannerScreen.Assignments.name) {
                AssignmentList(
                    assignments = Datasource().loadAssignments(),
                    modules = Datasource().loadModules(),
                    modifier = Modifier.fillMaxSize(),
                    onAssignmentClick = {
                        viewModel.selectAssignment(it)
                        navController.navigate(StudyPlannerScreen.AssignmentDetails.name)
                    }
                )
            }
            composable(route = StudyPlannerScreen.AssignmentDetails.name) {
                val assignment = uiState.selectedAssignment
                AssignmentDetailsScreen(
                    assignment = assignment,
                    moduleName = Datasource().loadModules()
                        .find { it.id == assignment.moduleId }?.name ?: "",
                    modifier = Modifier.fillMaxSize(),
                    onStartFocusButtonClicked = {
                        navController.navigate(StudyPlannerScreen.Focus.name)
                    },
                    onEditButtonClicked = {
                        navController.navigate(StudyPlannerScreen.AssignmentEntry.name)
                    }
                )
            }
            composable(route = StudyPlannerScreen.AssignmentEntry.name) {
                AssignmentEntryScreen(
                    modifier = Modifier.fillMaxSize(),
                    onCancelButtonClicked = { navController.navigateUp() },
                    onSaveButtonClicked = { navController.navigateUp() }
                )
            }
            composable(route = StudyPlannerScreen.Modules.name) {
                ModulesScreen(
                    modules = Datasource().loadModules(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = StudyPlannerScreen.Focus.name) {
                FocusScreen(
                    assignmentTitle = uiState.selectedAssignment.title,
                    timerText = "25:00",
                    statusText = stringResource(R.string.place_phone_face_down),
                    distractions = 0,
                    lightLevel = 320,
                    showLightWarning = false,
                    modifier = Modifier.fillMaxSize(),
                    onFinishButtonClicked = { navController.navigateUp() }
                )
            }
        }
    }
}