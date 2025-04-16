package com.example.encyclopedia.data.local

import android.content.Context
import com.example.encyclopedia.data.repository.OfflineQuestionsRepository
import com.example.encyclopedia.data.repository.QuestionsRepository


interface AppContainer {
    val questionsRepository: QuestionsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val questionsRepository: QuestionsRepository by lazy {
        OfflineQuestionsRepository(QuizDatabase.getDatabase(context))
    }
}
