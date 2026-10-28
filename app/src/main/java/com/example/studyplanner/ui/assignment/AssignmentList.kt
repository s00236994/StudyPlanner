package com.example.studyplanner.ui.assignment

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.data.Module
import com.example.studyplanner.ui.theme.StudyPlannerTheme


@Composable
fun AssignmentList(
    assignments: List<Assignment>,
    modules: List<Module>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(assignments) { assignment ->
            val moduleName = modules.find { it.id == assignment.moduleId }?.name ?: ""
            AssignmentCard(
                assignment = assignment,
                moduleName = moduleName,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentListPreview() {
    StudyPlannerTheme {
        AssignmentList(
            assignments = Datasource().loadAssignments(),
            modules = Datasource().loadModules()
        )
    }
}