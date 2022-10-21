package com.compose.movies.domain.repositories.suggested

import com.compose.movies.BuildConfig
import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.ApiService
import com.compose.movies.domain.network.Response
import com.compose.movies.framework.AppConstants.LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSuggestedShowsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : TvSuggestedShowsRepository {



    override suspend fun getSuggestedList(tvId: Int): Flow<Response<List<Show>>> = flow {
        emit(Response.Loading)
        val newResult =
            apiService.getSuggestedListShows(tvId.toString(),
                BuildConfig.API_KEY,
                LANGUAGE,
                "1")
        val cache = mutableListOf<Show>()
        cache.addAll(newResult.results.map {
            Show(it.id, it.name, it.overView, it.voteAverage, it.posterPatch ?: "")
        })
        emit(Response.Success(cache))

    }.catch { exception ->
        emit(Response.Error(exception))
    }
}