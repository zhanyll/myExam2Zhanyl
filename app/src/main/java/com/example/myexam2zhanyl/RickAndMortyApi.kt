package com.example.myexam2zhanyl

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    fun getAllCharacters(): Observable<Response>

    @GET("character/{id}")
    fun getById(@Path("id") id: Long): Single<Character>
}