package com.example.encyclopedia.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "quiz_results",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["userId"])]
)
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val score: Int,
    val totalQuestions:Int,
    val duration: Long,
    val timestamp: Long ,
    val userId: Int
)
