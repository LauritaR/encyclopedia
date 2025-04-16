package com.example.encyclopedia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category:String,
    val questionText:String,
    val option1:String,
    val option2:String,
    val option3:String,
    val option4:String,
    val correctOption:Int
)
