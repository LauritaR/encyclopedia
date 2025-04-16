package com.example.encyclopedia.presentation.navigation


import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.encyclopedia.data.local.QuizDatabase
import com.example.encyclopedia.data.repository.OfflineQuestionsRepository
import com.example.encyclopedia.presentation.screens.Homescreen
import com.example.encyclopedia.presentation.screens.InfoScreen
import com.example.encyclopedia.presentation.screens.Listscreen
import com.example.encyclopedia.presentation.screens.QuizScreen
import com.example.encyclopedia.presentation.screens.ResultScreen
import com.example.encyclopedia.presentation.viewmodel.QuizViewModel


@Composable
fun NavGraph(navController: NavHostController=rememberNavController(),
                 applicationContext: Context) {
    val quizDatabase = QuizDatabase.getDatabase(applicationContext)
    QuizDatabase.populateInitialData(applicationContext)

    val questionsRepository = OfflineQuestionsRepository(quizDatabase)
    val quizViewModel = QuizViewModel(questionsRepository)
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
                }
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
                navController = navController
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
                }

            )
        }
    }
}
