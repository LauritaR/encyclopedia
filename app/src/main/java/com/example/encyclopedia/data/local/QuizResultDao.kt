package com.example.encyclopedia.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insertResult(result: QuizResult)

    @Query("SELECT * FROM quiz_results WHERE userId = :userId ORDER BY timestamp DESC")
    fun getResultsForUser(userId: Int): Flow<List<QuizResult>>
}
