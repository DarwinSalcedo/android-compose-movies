package com.compose.movies.domain.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/3/tv/popular?")
    suspend fun getListShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
    ): ShowDto

    @GET("/3/tv/{tv_id}/similar?")
    suspend fun getSuggestedListShows(
        @Path("tv_id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String,
    ): ShowDto

}