package com.example.studyplanner.ui.focus

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studyplanner.R
import com.example.studyplanner.ui.theme.StudyPlannerTheme

@Composable
fun FocusScreen(
    assignmentTitle: String,
    timerText: String,
    statusText: String,
    distractions: Int,
    lightLevel: Int,
    showLightWarning: Boolean,
    modifier: Modifier = Modifier,
    isLandscape: Boolean = false,
    isRunning: Boolean = false,
    onStartButtonClicked: () -> Unit = {},
    onPauseButtonClicked: () -> Unit = {},
    onFinishButtonClicked: () -> Unit = {}
) {
    if (isLandscape) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FocusTimer(
                assignmentTitle = assignmentTitle,
                timerText = timerText,
                modifier = Modifier.weight(1f)
            )
            FocusDetails(
                statusText = statusText,
                distractions = distractions,
                lightLevel = lightLevel,
                isRunning = isRunning,
                onStartButtonClicked = onStartButtonClicked,
                onPauseButtonClicked = onPauseButtonClicked,
                showLightWarning = showLightWarning,
                onFinishButtonClicked = onFinishButtonClicked,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FocusTimer(
                assignmentTitle = assignmentTitle,
                timerText = timerText
            )
            FocusDetails(
                statusText = statusText,
                distractions = distractions,
                lightLevel = lightLevel,
                isRunning = isRunning,
                onStartButtonClicked = onStartButtonClicked,
                onPauseButtonClicked = onPauseButtonClicked,
                showLightWarning = showLightWarning,
                onFinishButtonClicked = onFinishButtonClicked
            )
        }
    }
}

@Composable
fun FocusTimer(
    assignmentTitle: String,
    timerText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.studying_title, assignmentTitle),
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
    }
}

@Composable
fun FocusDetails(
    statusText: String,
    distractions: Int,
    lightLevel: Int,
    showLightWarning: Boolean,
    onFinishButtonClicked: () -> Unit,
    isRunning: Boolean,
    onStartButtonClicked: () -> Unit,
    onPauseButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.bodyLarge
        )

        if (showLightWarning) {
            Text(
                text = stringResource(R.string.too_dark_warning),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            StatCard(
                label = stringResource(R.string.distractions),
                value = "$distractions",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                label = stringResource(R.string.light),
                value = stringResource(R.string.lux_value, lightLevel),
                modifier = Modifier.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = if (isRunning) onPauseButtonClicked else onStartButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (isRunning) {
                        stringResource(R.string.pause)
                    } else {
                        stringResource(R.string.start)
                    }
                )
            }
            OutlinedButton(
                onClick = onFinishButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.finish))
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

fun formatTime(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    val secondsText = if (seconds < 10) "0$seconds" else "$seconds"
    return "$minutes:$secondsText"
}


@Preview(showBackground = true, widthDp = 800, heightDp = 360)
@Composable
fun FocusScreenLandscapePreview() {
    StudyPlannerTheme {
        FocusScreen(
            assignmentTitle = "Mobile App Project",
            timerText = "12:40",
            statusText = "Paused. Phone picked up",
            distractions = 2,
            lightLevel = 38,
            showLightWarning = true,
            isLandscape = true
        )
    }
}