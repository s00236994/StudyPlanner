package com.example.studyplanner.ui.assignment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun AssignmentCard(
    assignment: Assignment,
    moduleName: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = assignment.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = moduleName,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${assignment.priority} priority (${assignment.status})",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentCardPreview() {
    StudyPlannerTheme {
        AssignmentCard(
            assignment = Assignment(
                id = 1,
                moduleId = 1,
                title = "Mobile App Project",
                dueDate = 0L,
                priority = "High",
                status = "In progress",
                notes = "",
                photoPath = null
            ),
            moduleName = "Mobile App Development"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentCardDarkPreview() {
    StudyPlannerTheme(darkTheme = true) {
        AssignmentCard(
            assignment = Assignment(
                id = 1,
                moduleId = 1,
                title = "Mobile App Project",
                dueDate = 0L,
                priority = "High",
                status = "In progress",
                notes = "",
                photoPath = null
            ),
            moduleName = "Mobile App Development"
        )
    }
}