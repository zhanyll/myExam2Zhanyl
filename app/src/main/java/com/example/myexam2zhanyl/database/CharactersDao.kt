package com.example.myexam2zhanyl.database

import androidx.room.*

@Dao
interface CharactersDao {
    @Query("SELECT * FROM Characters")
    fun getAll(): List<Characters>

    @Query("SELECT * FROM Characters WHERE id = :id")
    fun getById(id: Long?): Characters

    @Insert
    fun insert(character: Characters): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(characters: List<Characters>)

    @Update
    fun update(character: Characters)

    @Delete
    fun delete(character: Characters)
}