package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.data.local.QuizResult
import com.example.encyclopedia.data.local.QuizResultDao
import com.example.encyclopedia.presentation.viewmodel.UserResultsViewModel
import com.example.encyclopedia.presentation.viewmodel.UserResultsViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserResultsScreen(userId: Int, quizResultDao: QuizResultDao) {
    val viewModel: UserResultsViewModel = viewModel(
        factory = UserResultsViewModelFactory(quizResultDao)
    )
    val results by viewModel.getUserResults(userId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Your results")
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)) {
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

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)) {
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
