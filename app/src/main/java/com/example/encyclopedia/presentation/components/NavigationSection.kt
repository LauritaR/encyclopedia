package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Accent5
import com.example.encyclopedia.presentation.theme.Main60


@Composable
fun NavigationSection(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    isLastQuestion: Boolean,
    onSubmit: () -> Unit,
    canGoBack: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (canGoBack) {
            Button(onClick = onPrevious,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main60,
                    contentColor = Accent10
                )) {
                Text("Previous")
            }
        }

        if (isLastQuestion) {
            Button (onClick = onSubmit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main60,
                    contentColor = Accent10
                )
            ) {
                Text("Submit")
            }
        } else {
            Button(onClick = onNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Main60,
                    contentColor = Accent10
                )) {
                Text("Next")
            }
        }
    }
}