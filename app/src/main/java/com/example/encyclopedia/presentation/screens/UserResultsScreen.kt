package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.data.local.QuizResult
import com.example.encyclopedia.data.local.QuizResultDao
import com.example.encyclopedia.presentation.components.TopBar
import com.example.encyclopedia.presentation.theme.Accent10
import com.example.encyclopedia.presentation.theme.Main30
import com.example.encyclopedia.presentation.theme.Main60
import com.example.encyclopedia.presentation.theme.Secondary30
import com.example.encyclopedia.presentation.viewmodel.UserResultsViewModel
import com.example.encyclopedia.presentation.viewmodel.UserResultsViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserResultsScreen(userId: Int, quizResultDao: QuizResultDao,
                      onNavigateToHome:()->Unit) {
    val viewModel: UserResultsViewModel = viewModel(
        factory = UserResultsViewModelFactory(quizResultDao)
    )
    val results by viewModel.getUserResults(userId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        TopBar()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(30.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Main30)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Your previous results",
                    fontSize = 20.sp,
                    color = Accent10,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            if (results.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No results found")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(results) { result ->
                            ResultCard(result)
                        }
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = onNavigateToHome,
                border = BorderStroke(2.dp, Main60),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Main60,
                    containerColor = Color.Transparent
                )
            )
            {
                Text("Back to Home")
            }
        }
    }
}


@Composable
fun ResultCard(result: QuizResult) {
    val formattedDate = remember(result.timestamp) {
        SimpleDateFormat("dd MMM yyyy • HH:mm", Locale.getDefault()).format(Date(result.timestamp))
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Category: ${result.category}", style = MaterialTheme.typography.titleMedium)
            Text("Score: ${result.score}/${result.totalQuestions}")
            Text("Date: $formattedDate")
        }
    }
}
