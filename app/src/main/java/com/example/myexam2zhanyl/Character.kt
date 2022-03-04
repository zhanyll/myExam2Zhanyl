package com.example.myexam2zhanyl

import androidx.room.PrimaryKey

data class Response(
    val results: List<Character>
)

data class Character(
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
