package com.example.encyclopedia.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnswerDao {

    @Insert
    suspend fun insertAnswer(answer: Answer)

    @Query("SELECT * from user_answers WHERE questionId = :questionId")
    suspend fun getAnswersForQuestion(questionId:Int):List<Answer>
}


