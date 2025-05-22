package com.example.encyclopedia.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.encyclopedia.data.local.QuizDatabase
import com.example.encyclopedia.data.local.QuizResult
import com.example.encyclopedia.data.local.User
import com.example.encyclopedia.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = QuizDatabase.getDatabase(application).userDao()
    private val quizResultDao = QuizDatabase.getDatabase(application).quizResultDao()
    private val repository = UserRepository(
        userDao,
        quizResultDao = quizResultDao
    )
    var currentUser by mutableStateOf<User?>(null)
            private set
    private val _loggedInUserId = mutableStateOf<Int?>(null)
    val loggedInUserId: State<Int?> = _loggedInUserId
    
    fun updateCurrentUser(user:User){
        currentUser=user
    }
    fun saveQuizResult(score: Int, category: String, duration:Long, totalQuestions:Int){
        currentUser?.let { user->
            val result = QuizResult(
                category = category,
                score = score,
                userId = user.id,
                totalQuestions = totalQuestions,
                duration =duration,
                timestamp = System.currentTimeMillis()
            )
            viewModelScope.launch {
                repository.saveQuizResult(result)
            }
        }
    }
    fun registerUser(username: String, email: String, password: String, onResult: (Boolean,String) -> Unit) {
        viewModelScope.launch {
            val result = repository.registerUser(username, email, password)
            if (result) {
                onResult(true, "Registration successful!")
            } else {
                onResult(false, "Registration failed. Email might already be in use.")
            }
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val user = repository.loginUser(email, password)
            if (user!=null) {
                updateCurrentUser(user)
                _loggedInUserId.value =user.id
                onResult(true, "Login successful!")
            } else {
                onResult(false, "Invalid email or password.")
            }
        }
    }
    fun logout(){
        currentUser = null
        _loggedInUserId.value =null
    }
}
