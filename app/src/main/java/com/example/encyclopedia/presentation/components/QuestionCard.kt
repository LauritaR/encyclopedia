package com.example.encyclopedia.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.encyclopedia.data.local.Question
import com.example.encyclopedia.presentation.theme.Main60


@Composable
fun QuestionCard(question: Question, onAnswerSelected:(Int)->Unit, selectedOption:Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Main60)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = question.questionText, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            val options = listOf(
                question.option1,
                question.option2,
                question.option3,
                question.option4
            )

            options.forEachIndexed { index, option ->
                OptionRow(
                    option = option,
                    isSelected = selectedOption == index+1,
                    onSelected = { onAnswerSelected(index + 1) }

                )
            }
        }
    }
}