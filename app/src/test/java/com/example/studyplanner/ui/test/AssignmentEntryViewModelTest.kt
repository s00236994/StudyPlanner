package com.example.studyplanner.ui.test

import com.example.studyplanner.ui.assignment.AssignmentEntryViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AssignmentEntryViewModelTest {

    private val viewModel = AssignmentEntryViewModel()

    @Test
    fun assignmentEntryViewModel_Initialization_FieldsEmptyAndNoError() {
        val uiState = viewModel.uiState.value

        assertEquals("", viewModel.title)
        assertEquals("", viewModel.notes)
        assertFalse(uiState.isTitleError)
    }

    @Test
    fun assignmentEntryViewModel_BlankTitleSaved_ErrorFlagSet() {
        val isSaved = viewModel.saveAssignment()
        val uiState = viewModel.uiState.value

        assertFalse(isSaved)
        assertTrue(uiState.isTitleError)
    }

    @Test
    fun assignmentEntryViewModel_TitleOnlySpacesSaved_ErrorFlagSet() {
        viewModel.updateTitle("   ")

        val isSaved = viewModel.saveAssignment()
        val uiState = viewModel.uiState.value

        assertFalse(isSaved)
        assertTrue(uiState.isTitleError)
    }

    @Test
    fun assignmentEntryViewModel_ValidTitleSaved_ErrorFlagUnset() {
        viewModel.updateTitle("Mobile App Project")

        val isSaved = viewModel.saveAssignment()
        val uiState = viewModel.uiState.value

        assertTrue(isSaved)
        assertFalse(uiState.isTitleError)
    }

    @Test
    fun assignmentEntryViewModel_ErrorThenFixed_ErrorFlagCleared() {
        viewModel.saveAssignment()
        viewModel.updateTitle("Lab 3 worksheet")
        viewModel.saveAssignment()

        val uiState = viewModel.uiState.value

        assertFalse(uiState.isTitleError)
    }
}