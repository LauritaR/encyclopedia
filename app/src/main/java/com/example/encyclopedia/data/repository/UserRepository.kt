package com.example.encyclopedia.data.repository

import com.example.encyclopedia.data.local.QuizResult
import com.example.encyclopedia.data.local.QuizResultDao
import com.example.encyclopedia.data.local.User
import com.example.encyclopedia.data.local.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao,
    private val quizResultDao: QuizResultDao
) {
    suspend fun registerUser(username: String, email: String, password: String): Boolean {
        val existing = userDao.getUserByEmail(email)
        return if (existing == null) {
            userDao.insertUser(User(username = username, email = email, password = password))
            true
        } else {
            false
        }
    }
    suspend fun loginUser(email: String, password: String): User? {
       return userDao.getUserByEmailAndPassword(email, password)

    }

    suspend fun saveQuizResult(result:QuizResult) {
        quizResultDao.insertResult(result)
    }

    suspend fun getResultForUser(userId:Int): Flow<List<QuizResult>> {
        return quizResultDao.getResultsForUser(userId)
    }
}
