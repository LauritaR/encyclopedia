package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Secondary30
import com.example.encyclopedia.presentation.viewmodel.UserViewModel


@Composable

fun ResultScreen(score:Int,
                 totalQuestions:Int,
                    onNavigateToHome:()->Unit,
                 onNavigateToUserResults:()-> Unit,
                 userViewModel: UserViewModel)
{
    val context = LocalContext.current
    val loggedInUserId = userViewModel.loggedInUserId.value
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp)),

            colors = CardDefaults.cardColors(containerColor = Secondary30)
        ) {
            Column(modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Quiz Completed!",
                    style=MaterialTheme.typography.titleLarge,
                    color = Accent10
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your score is",
                    style=MaterialTheme.typography.titleMedium,
                    color = Accent10
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text="$score/ $totalQuestions",
                    color = Accent10
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(onClick = onNavigateToHome,
                        colors= ButtonDefaults.buttonColors(
                            contentColor = Secondary30,
                            containerColor = Accent10
                        )) {
                        Text("Back to Home")
                    }
                }
                if (loggedInUserId != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Button(
                            onClick = onNavigateToUserResults,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Secondary30,
                                containerColor = Accent10
                            )
                        ) {
                            Text("View My Past Results")
                        }
                    }
                }

            }
        }
    }
}
