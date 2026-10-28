package com.example.studyplanner.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.data.Assignment
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.data.Module
import com.example.studyplanner.ui.assignment.AssignmentCard
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun DashboardScreen(
    assignments: List<Assignment>,
    modules: List<Module>,
    focusMinutes: Int,
    modifier: Modifier = Modifier
) {
    val now = System.currentTimeMillis()
    val oneWeek = 7 * 86_400_000L

    val overdue = assignments.filter { it.dueDate < now && it.status != "Done" }
    val dueThisWeek = assignments.filter {
        it.dueDate >= now && it.dueDate <= now + oneWeek && it.status != "Done"
    }

    LazyColumn(modifier = modifier.padding(horizontal = 8.dp)) {
        item {
            FocusSummaryCard(
                focusMinutes = focusMinutes,
                modifier = Modifier.padding(8.dp)
            )
        }
        item {
            SectionTitle(text = "Overdue (${overdue.size})")
        }
        items(overdue) { assignment ->
            AssignmentCard(
                assignment = assignment,
                moduleName = modules.find { it.id == assignment.moduleId }?.name ?: "",
                modifier = Modifier.padding(8.dp)
            )
        }
        item {
            SectionTitle(text = "Due this week (${dueThisWeek.size})")
        }
        items(dueThisWeek) { assignment ->
            AssignmentCard(
                assignment = assignment,
                moduleName = modules.find { it.id == assignment.moduleId }?.name ?: "",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun FocusSummaryCard(
    focusMinutes: Int,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Focus time this week",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${focusMinutes / 60}h ${focusMinutes % 60}m",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(start = 8.dp, top = 16.dp, bottom = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    StudyPlannerTheme {
        DashboardScreen(
            assignments = Datasource().loadAssignments(),
            modules = Datasource().loadModules(),
            focusMinutes = 275
        )
    }
}