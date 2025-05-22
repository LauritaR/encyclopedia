package com.example.encyclopedia.presentation.navigation


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.data.local.QuizDatabase
import com.example.encyclopedia.data.repository.OfflineQuestionsRepository
import com.example.encyclopedia.presentation.screens.Homescreen
import com.example.encyclopedia.presentation.screens.InfoScreen
import com.example.encyclopedia.presentation.screens.Listscreen
import com.example.encyclopedia.presentation.screens.LoginScreen
import com.example.encyclopedia.presentation.screens.QuizScreen
import com.example.encyclopedia.presentation.screens.RegistrationScreen
import com.example.encyclopedia.presentation.screens.ResultScreen
import com.example.encyclopedia.presentation.screens.UserResultsScreen
import com.example.encyclopedia.presentation.viewmodel.QuizViewModel
import com.example.encyclopedia.presentation.viewmodel.UserViewModel

//TODO perziureti navigacijas

@Composable
fun NavGraph(navController: NavHostController=rememberNavController(),
                 applicationContext: Context) {
    val quizDatabase = QuizDatabase.getDatabase(applicationContext)
    QuizDatabase.populateInitialData(applicationContext)

    val questionsRepository = OfflineQuestionsRepository(quizDatabase)
    val quizViewModel = QuizViewModel(questionsRepository)
    val userViewModel = UserViewModel(application = applicationContext as Application)

    NavHost(
        navController = navController,
        startDestination = "homescreen"
    ) {
        composable(route = "homescreen") {
            Homescreen(
                onNavigateToInfo = { categoryTitle ->
                    navController.navigate("listScreen/$categoryTitle")
                },
                onNavigateToQuiz = { categoryTitle ->
                    navController.navigate("quizScreen/$categoryTitle")
                },
                onNavigateToLogin = {
                    navController.navigate("loginScreen")
                },
                onNavigateToRegister = {
                    navController.navigate("registerScreen")
                },
                userViewModel=userViewModel

            )
        }
        composable(route = "loginScreen") {
            LoginScreen(
                onLoginClicked = { email, password ->
                    userViewModel.loginUser(email, password) { success, message ->
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                        if (success) {
                            navController.navigate("homescreen") {
                                popUpTo("homescreen") { inclusive = true }
                            }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }


        composable(route = "registerScreen") {
            RegistrationScreen(
                onRegisterSuccess = { success, message ->
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                        if (success) {
                            navController.popBackStack()
                        }
                },
                onBack = { navController.popBackStack() },
                viewModel = userViewModel
            )
        }
        composable("userResultsScreen/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: return@composable
            UserResultsScreen(
                userId = userId,
                quizResultDao = quizDatabase.quizResultDao()
            )
        }


        composable(route = "listScreen/{categoryTitle}") { backStackEntry ->
            val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""
            Listscreen(
                categoryTitle = categoryTitle,
                navController = navController,
                onNavigateToInfo = { categoryTitle, index ->
                    navController.navigate("infoScreen/$categoryTitle/$index")
                    Log.d(
                        "ListScreen",
                        "Navigating to InfoScreen: categoryTitle=$categoryTitle, index=$index"
                    )

                })
        }
        composable(route = "infoScreen/{categoryTitle}/{pageid}") { backStackEntry ->
            val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""
            val pageid = backStackEntry.arguments?.getString("pageid")?.toInt() ?: 0
            Log.d("InfoScreen", "Navigated to InfoScreen with categoryTitle=$categoryTitle, pageid=$pageid")
            InfoScreen(
                categoryTitle = categoryTitle,
                currentIndex = pageid,
                navController = navController
            )
        }
        composable(route = "quizScreen/{categoryTitle}") { backStackEntry ->
            val categoryTitle = backStackEntry.arguments?.getString("categoryTitle") ?: ""
            Log.d("QuizScreen", "Navigating to quiz with category: $categoryTitle")
            QuizScreen(
                viewModel = quizViewModel,
                category = categoryTitle,
                navController = navController,
                userViewModel=userViewModel
            )
        }
        composable(route = "resultScreen/{score}/{totalQuestions}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0
            val totalQuestions = backStackEntry.arguments?.getString("totalQuestions")?.toInt() ?: 0
            ResultScreen(
                score = score,
                totalQuestions = totalQuestions,
                onNavigateToHome = {
                    navController.navigate("homescreen") {
                        popUpTo("homescreen") { inclusive = true }
                    }
                },
                onNavigateToUserResults ={
                    val userId = userViewModel.loggedInUserId.value ?: return@ResultScreen
                    navController.navigate("userResultsScreen/$userId")
                },
                userViewModel=userViewModel

            )
        }
    }
}
