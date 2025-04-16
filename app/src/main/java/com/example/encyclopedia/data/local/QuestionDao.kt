package com.example.encyclopedia.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query

@Dao
interface QuestionDao{
    @Insert
    suspend fun insertQuestions(question: List<Question>)

    @Update
    suspend fun updateQuestions(question: Question)

    @Delete
    suspend fun deleteQuestion(question:Question)

    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions():List<Question>

    @Query("SELECT * FROM questions WHERE category= :category")
    suspend fun getQuestionByCategory(category:String):List<Question>


}
