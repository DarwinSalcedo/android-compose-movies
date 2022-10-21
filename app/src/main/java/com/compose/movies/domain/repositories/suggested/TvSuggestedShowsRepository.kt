package com.compose.movies.domain.repositories.suggested

import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.Response
import kotlinx.coroutines.flow.Flow

interface TvSuggestedShowsRepository {

    suspend fun getSuggestedList(tvId: Int): Flow<Response<List<Show>>>

}