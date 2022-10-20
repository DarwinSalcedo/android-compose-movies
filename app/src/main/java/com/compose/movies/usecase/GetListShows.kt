package com.compose.movies.usecase

import com.compose.movies.domain.repositories.TvShowsRepository
import javax.inject.Inject

class GetListShows @Inject constructor(private val repository: TvShowsRepository) {

    suspend operator fun invoke() = repository.getList()

}
