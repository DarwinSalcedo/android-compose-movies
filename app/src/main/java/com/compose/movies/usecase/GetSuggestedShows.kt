package com.compose.movies.usecase

import com.compose.movies.domain.repositories.suggested.TvSuggestedShowsRepository
import javax.inject.Inject

class GetSuggestedShows @Inject constructor(private val repository: TvSuggestedShowsRepository) {

    suspend operator fun invoke(tvId: Int) = repository.getSuggestedList(tvId)

}
