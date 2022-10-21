package com.compose.movies.domain.repositories.show

import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.Response
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun getList(): Flow<Response<List<Show>>>

}