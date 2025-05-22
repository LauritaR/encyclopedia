package com.example.encyclopedia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email:String,
    val password: String,
    val username: String,
    val isLoggedIn: Boolean = false
)
