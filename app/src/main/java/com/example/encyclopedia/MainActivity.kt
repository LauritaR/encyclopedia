package com.example.encyclopedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.room.Room
import com.example.encyclopedia.data.local.QuizDatabase
import com.example.encyclopedia.data.repository.OfflineQuestionsRepository

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database = Room.databaseBuilder(
                applicationContext,
                QuizDatabase::class.java,
                "quiz_database"
            ).build()

            val windowClass = calculateWindowSizeClass(this)
            val questionsRepository = OfflineQuestionsRepository(database)

            EncyclopediaApp(windowClass= windowClass.widthSizeClass,
                questionsRepository = questionsRepository,
                appContext = applicationContext)
            }
        }
    }

