package com.compose.movies.domain.repositories

import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.Response
import kotlinx.coroutines.flow.Flow

class TvShowsRepositoryImpl() :TvShowsRepository {
    override fun getList(countryCode: String): Flow<Response<List<Show>>> {
        TODO("Not yet implemented")
    }
}