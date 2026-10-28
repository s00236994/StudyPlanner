package com.example.studyplanner.ui.assignment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun AssignmentDetailsScreen(
    assignment: Assignment,
    moduleName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = assignment.title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "$moduleName (${dueText(assignment.dueDate)})",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "${assignment.priority} priority (${assignment.status})",
            style = MaterialTheme.typography.bodyMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No photo yet")
            }
        }

        Text(
            text = "Notes",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = if (assignment.notes.isEmpty()) "No notes" else assignment.notes,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Focus sessions",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "No focus sessions yet",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start focus session")
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Edit")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Done")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Delete")
            }
        }
    }
}

fun dueText(dueDate: Long): String {
    val now = System.currentTimeMillis()
    val oneDay = 86_400_000L
    return if (dueDate < now) {
        val days = (now - dueDate) / oneDay
        "Overdue by $days days"
    } else {
        val days = (dueDate - now + oneDay - 1) / oneDay
        "Due in $days days"
    }
}

@Preview(showBackground = true)
@Composable
fun AssignmentDetailsScreenPreview() {
    StudyPlannerTheme {
        AssignmentDetailsScreen(
            assignment = Datasource().loadAssignments()[5],
            moduleName = "Mobile App Development"
        )
    }
}