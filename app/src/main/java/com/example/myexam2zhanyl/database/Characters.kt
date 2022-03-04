package com.example.myexam2zhanyl.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Characters(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String
)
