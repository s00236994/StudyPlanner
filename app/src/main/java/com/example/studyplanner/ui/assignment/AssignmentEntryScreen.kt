package com.example.studyplanner.ui.assignment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun AssignmentEntryScreen(
    modifier: Modifier = Modifier,
    viewModel: AssignmentEntryViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add assignment",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = viewModel.title,
            onValueChange = { viewModel.updateTitle(it) },
            label = {
                if (uiState.isTitleError) {
                    Text(text = "Title is required")
                } else {
                    Text(text = "Title")
                }
            },
            isError = uiState.isTitleError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.notes,
            onValueChange = { viewModel.updateNotes(it) },
            label = { Text(text = "Notes (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = { viewModel.saveAssignment() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentEntryScreenPreview() {
    StudyPlannerTheme {
        AssignmentEntryScreen()
    }
}