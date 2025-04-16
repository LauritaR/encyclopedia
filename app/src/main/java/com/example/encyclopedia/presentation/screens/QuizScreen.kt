package com.example.encyclopedia.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.encyclopedia.data.local.Question
import com.example.encyclopedia.presentation.components.HeaderSection
import com.example.encyclopedia.presentation.components.LinearTimeProgress
import com.example.encyclopedia.presentation.components.NavigationSection
import com.example.encyclopedia.presentation.components.QuestionCard
import com.example.encyclopedia.presentation.viewmodel.QuizViewModel

@Composable
fun QuizScreen(modifier: Modifier=Modifier,
               viewModel: QuizViewModel,
               category:String,
               navController: NavController
) {

    LaunchedEffect(category) {

        viewModel.loadQuestions(category)
    }
    val questions = viewModel.questionsFlow.collectAsState(initial = emptyList())
    val currentQuestionIndex = viewModel.currentQuestionIndexFlow.collectAsState(initial = 0)
    val score = viewModel.scoreFlow.collectAsState(initial = 0)
    var elapsedTime by remember { mutableStateOf(0L) }
    if (questions.value.isEmpty()) {
        CircularProgressIndicator()
        return
    }
    val question = questions.value.getOrNull(currentQuestionIndex.value) ?: return

    val selectedOptions = remember { mutableStateOf(mutableMapOf<Int, Int>()) }
    val currentQuestionId = question.id

    val selectedOption = selectedOptions.value[currentQuestionId] ?: 0

    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier=Modifier.size(100.dp))
            HeaderSection(
                score = score.value,
                currentQuestion = currentQuestionIndex.value + 1,
                totalQuestions = questions.value.size
            )
            LinearTimeProgress(onTimeElapsed = {time->
                elapsedTime = time
            })
            Spacer(modifier=Modifier.padding(16.dp))
            QuestionCard(
                question = question,
                onAnswerSelected = { selectedOptionValue ->
                    viewModel.saveUserAnswer(
                        questionId = currentQuestionId,
                        selectedOption = selectedOptionValue,
                        answerTime = elapsedTime
                    )
                    selectedOptions.value = selectedOptions.value.toMutableMap().apply {
                        this[currentQuestionId] = selectedOptionValue
                    }
                },
                selectedOption = selectedOption
            )
            NavigationSection(
                onPrevious = { viewModel.goToPreviousQuestion() },
                onNext = { viewModel.goToNextQuestion() },
                isLastQuestion = currentQuestionIndex.value == questions.value.size - 1,
                onSubmit = {
                    viewModel.calculateScore()

                    val scoreString = score.value.toString()
                    val totalQuestionsString = questions.value.size.toString()

                   navController.navigate("resultScreen/$scoreString/$totalQuestionsString")},
                canGoBack = currentQuestionIndex.value > 0
            )
        }
    }
}
