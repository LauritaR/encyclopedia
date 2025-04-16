package com.example.encyclopedia.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_answers")
data class Answer(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val questionId:Int,
    val selectedOption:Int,
    val answerTime:Long
)

