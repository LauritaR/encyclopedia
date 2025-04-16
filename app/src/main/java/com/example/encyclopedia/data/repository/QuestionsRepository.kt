package com.example.encyclopedia.data.repository

import com.example.encyclopedia.data.local.Answer
import com.example.encyclopedia.data.local.Question


interface QuestionsRepository {
    suspend fun getQuestionsByCategory(category:String):List<Question>

    suspend fun insertQuestions(questions:List<Question>)

    suspend fun updateQuestion(question: Question)

    suspend fun deleteQuestion(question:Question)

    suspend fun deleteAllQuestions()

    suspend fun saveUserAnswer(answer: Answer)
}
