package com.example.studyplanner.ui.module

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.data.Datasource
import com.example.studyplanner.data.Module
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun ModulesScreen(
    modules: List<Module>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(horizontal = 8.dp)) {
        items(modules) { module ->
            ModuleCard(
                module = module,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ModuleCard(
    module: Module,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = module.code,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = module.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            TextButton(onClick = { }) {
                Text(text = "Edit")
            }
            TextButton(onClick = { }) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModulesScreenPreview() {
    StudyPlannerTheme {
        ModulesScreen(modules = Datasource().loadModules())
    }
}