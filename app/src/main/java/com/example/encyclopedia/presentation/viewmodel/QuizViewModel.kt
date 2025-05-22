package com.example.encyclopedia.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.encyclopedia.data.local.Answer
import com.example.encyclopedia.data.local.Question
import com.example.encyclopedia.data.repository.QuestionsRepository

import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch


class QuizViewModel(private val questionsRepository: QuestionsRepository):ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questionsFlow: StateFlow<List<Question>> = _questions

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndexFlow: StateFlow<Int> = _currentQuestionIndex

    private val _userAnswers = MutableStateFlow<List<Answer>>(emptyList())
    val userAnswerFlow: StateFlow<List<Answer>> = _userAnswers

    private val _score = MutableStateFlow(0)
    val scoreFlow: StateFlow<Int> = _score


    fun loadQuestions(category: String) {
        viewModelScope.launch {
            _questions.value = questionsRepository.getQuestionsByCategory(category)
        }
    }

    fun saveUserAnswer(questionId: Int, selectedOption: Int, answerTime: Long) {
        viewModelScope.launch {
            val newAnswer = Answer(
                questionId = questionId,
                selectedOption = selectedOption,
                answerTime = answerTime
            )

            _userAnswers.value = _userAnswers.value + newAnswer

            val question = _questions.value.find { it.id == questionId }
            if (question != null && question.correctOption == selectedOption) {
                _score.value += 1
            }

            questionsRepository.saveUserAnswer(newAnswer)
        }
    }
    fun resetCurrentQuestionIndex(){
        _currentQuestionIndex.value =0
    }
    fun goToNextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun goToPreviousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }
    fun calculateScore(){
        val userAnswerMap = _userAnswers.value.associateBy { it.questionId }

        var calculatedScore=0
        _questions.value.forEach {
                question ->
            val userAnswer=userAnswerMap[question.id]?.selectedOption
            if(userAnswer!=null && userAnswer == question.correctOption){
                calculatedScore++
            }
        }
        _score.value=calculatedScore
        Log.d("QuizViewModel", "Calculated score: $_score.value")
    }


}
