package com.compose.movies.domain.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // "/3/tv/popular?api_key=_________________&language=en-US&page=2"
    @GET("/3/tv/popular?")
    suspend fun getListShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
    ): ShowDto

}