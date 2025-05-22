package com.example.encyclopedia.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.encyclopedia.data.local.QuizResult
import com.example.encyclopedia.data.local.QuizResultDao
import kotlinx.coroutines.flow.Flow

class UserResultsViewModel(private val dao: QuizResultDao) : ViewModel() {
    fun getUserResults(userId: Int): Flow<List<QuizResult>> {
        return dao.getResultsForUser(userId)
    }
}

class UserResultsViewModelFactory(
    private val dao: QuizResultDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserResultsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserResultsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
