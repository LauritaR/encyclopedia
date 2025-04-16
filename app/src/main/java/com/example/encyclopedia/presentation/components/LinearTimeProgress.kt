package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun LinearTimeProgress(modifier: Modifier = Modifier, onTimeElapsed:(Long)->Unit) {
    val remainingTime = remember { mutableStateOf(60) } // Remaining time in seconds
    val progress = remember { mutableStateOf(1f) } // Progress starts at full (1.0)

    LaunchedEffect(Unit) {
        while (remainingTime.value > 0) {
            delay(1000L) // Wait for 1 second
            remainingTime.value -= 1
            progress.value = remainingTime.value / 60.toFloat()
        }
    }

    val elapsedTime = 60 - remainingTime.value
    LaunchedEffect(elapsedTime) {
        onTimeElapsed(elapsedTime.toLong())
    }
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
    )
    {
        Row {
            LinearProgressIndicator(
                progress = progress.value,
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp),
                color = Color.LightGray,
            )
            Text(" ${remainingTime.value}s")
        }

    }
}