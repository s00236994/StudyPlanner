package com.example.studyplanner.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.studyplanner.R
import com.example.studyplanner.StudyPlannerApp
import com.example.studyplanner.StudyPlannerScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StudyPlannerScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupStudyPlannerNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            StudyPlannerApp(navController = navController)
        }
    }

    @Test
    fun studyPlannerNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(StudyPlannerScreen.Dashboard.name)
    }

    @Test
    fun studyPlannerNavHost_verifyBackNavigationNotShownOnDashboard() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun studyPlannerNavHost_clickAssignmentCard_navigatesToDetails() {
        navigateToDetails()
        navController.assertCurrentRouteName(StudyPlannerScreen.AssignmentDetails.name)
    }

    @Test
    fun studyPlannerNavHost_clickEditOnDetails_navigatesToEntry() {
        navigateToDetails()
        composeTestRule.onNodeWithStringId(R.string.edit).performClick()
        navController.assertCurrentRouteName(StudyPlannerScreen.AssignmentEntry.name)
    }

    @Test
    fun studyPlannerNavHost_clickCancelOnEntry_navigatesBackToDetails() {
        navigateToDetails()
        composeTestRule.onNodeWithStringId(R.string.edit).performClick()
        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
        navController.assertCurrentRouteName(StudyPlannerScreen.AssignmentDetails.name)
    }

    @Test
    fun studyPlannerNavHost_clickStartFocusOnDetails_navigatesToFocus() {
        navigateToDetails()
        composeTestRule.onNodeWithStringId(R.string.start_focus_session).performClick()
        navController.assertCurrentRouteName(StudyPlannerScreen.Focus.name)
    }

    @Test
    fun studyPlannerNavHost_clickBackOnDetails_navigatesToDashboard() {
        navigateToDetails()
        performNavigateUp()
        navController.assertCurrentRouteName(StudyPlannerScreen.Dashboard.name)
    }

    private fun navigateToDetails() {
        composeTestRule.onNodeWithText("Lab 3 worksheet").performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }
}