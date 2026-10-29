package com.example.studyplanner.ui.focus

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun FocusScreen(
    assignmentTitle: String,
    timerText: String,
    statusText: String,
    distractions: Int,
    lightLevel: Int,
    showLightWarning: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Studying: $assignmentTitle",
            style = MaterialTheme.typography.titleMedium
        )

        Box(
            modifier = Modifier
                .size(220.dp)
                .border(
                    width = 6.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = timerText,
                style = MaterialTheme.typography.displayMedium
            )
        }

        Text(
            text = statusText,
            style = MaterialTheme.typography.bodyLarge
        )

        if (showLightWarning) {
            Text(
                text = "Too dark to study. Turn on a light.",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(
                label = "Distractions",
                value = "$distractions",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = "Light",
                value = "$lightLevel lux",
                modifier = Modifier.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Start")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Composable
fun StatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FocusScreenPreview() {
    StudyPlannerTheme {
        FocusScreen(
            assignmentTitle = "Mobile App Project",
            timerText = "25:00",
            statusText = "Place phone face down to start",
            distractions = 0,
            lightLevel = 320,
            showLightWarning = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FocusScreenDarkRoomPreview() {
    StudyPlannerTheme {
        FocusScreen(
            assignmentTitle = "Mobile App Project",
            timerText = "12:40",
            statusText = "Paused. Phone picked up",
            distractions = 2,
            lightLevel = 38,
            showLightWarning = true
        )
    }
}