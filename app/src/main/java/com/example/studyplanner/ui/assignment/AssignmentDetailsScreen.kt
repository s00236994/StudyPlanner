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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.R
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun AssignmentDetailsScreen(
    assignment: Assignment,
    moduleName: String,
    modifier: Modifier = Modifier,
    onStartFocusButtonClicked: () -> Unit = {},
    onEditButtonClicked: () -> Unit = {},
    onDoneButtonClicked: () -> Unit = {},
    onDeleteButtonClicked: () -> Unit = {}
) {
    val dueText = if (assignment.dueDate < System.currentTimeMillis()) {
        stringResource(R.string.overdue_by_days, daysOverdue(assignment.dueDate))
    } else {
        stringResource(R.string.due_in_days, daysLeft(assignment.dueDate))
    }

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
            text = stringResource(R.string.module_and_due, moduleName, dueText),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = stringResource(
                R.string.priority_status,
                assignment.priority,
                assignment.status
            ),
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
                Text(text = stringResource(R.string.no_photo_yet))
            }
        }

        Text(
            text = stringResource(R.string.notes),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = if (assignment.notes.isEmpty()) {
                stringResource(R.string.no_notes)
            } else {
                assignment.notes
            },
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = stringResource(R.string.focus_sessions),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(R.string.no_focus_sessions_yet),
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = onStartFocusButtonClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.start_focus_session))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = onEditButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.edit))
            }
            OutlinedButton(
                onClick = onDoneButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.done))
            }
            OutlinedButton(
                onClick = onDeleteButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.delete))
            }
        }
    }
}

fun daysOverdue(dueDate: Long): Long {
    val oneDay = 86_400_000L
    return (System.currentTimeMillis() - dueDate) / oneDay
}

fun daysLeft(dueDate: Long): Long {
    val oneDay = 86_400_000L
    return (dueDate - System.currentTimeMillis() + oneDay - 1) / oneDay
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