package com.example.encyclopedia.data.repository

import com.example.encyclopedia.data.local.Answer
import com.example.encyclopedia.data.local.Question
import com.example.encyclopedia.data.local.QuizDatabase

class OfflineQuestionsRepository(private val database: QuizDatabase):QuestionsRepository {
    override suspend fun getQuestionsByCategory(category: String): List<Question> {
        return database.questionDao().getQuestionByCategory(category)
    }

    override suspend fun insertQuestions(questions: List<Question>) {
        database.questionDao().insertQuestions(questions)
    }

    override suspend fun updateQuestion(question: Question) {
        database.questionDao().updateQuestions(question)
    }

    override suspend fun deleteQuestion(question: Question) {
        database.questionDao().deleteQuestion(question)
    }

    override suspend fun deleteAllQuestions() {
        database.questionDao().deleteAllQuestions()
    }

    override suspend fun saveUserAnswer(answer: Answer) {
        database.answerDao().insertAnswer(answer)
    }
}
