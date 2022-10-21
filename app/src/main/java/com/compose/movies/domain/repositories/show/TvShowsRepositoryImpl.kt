package com.compose.movies.domain.repositories.show

import com.compose.movies.BuildConfig
import com.compose.movies.domain.model.Show
import com.compose.movies.domain.network.ApiService
import com.compose.movies.domain.network.Response
import com.compose.movies.framework.AppConstants.LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : TvShowsRepository {

    private var currentPage = 1
    private var cache = mutableListOf<Show>()


    override suspend fun getList(): Flow<Response<List<Show>>> = flow {
        emit(Response.Loading)
        val newResult =
            apiService.getListShows(BuildConfig.API_KEY, LANGUAGE, currentPage.toString())
        currentPage++
        cache.addAll(newResult.results.map {
            Show(it.id,it.name, it.overView, it.voteAverage, it.posterPatch ?: "")
        })
        emit(Response.Success(cache))

    }.catch { exception ->
        emit(Response.Error(exception))
    }
}