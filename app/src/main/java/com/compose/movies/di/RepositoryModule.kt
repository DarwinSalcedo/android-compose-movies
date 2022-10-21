package com.compose.movies.di


import com.compose.movies.domain.network.ApiService
import com.compose.movies.domain.repositories.show.TvShowsRepository
import com.compose.movies.domain.repositories.show.TvShowsRepositoryImpl
import com.compose.movies.domain.repositories.suggested.TvSuggestedShowsRepository
import com.compose.movies.domain.repositories.suggested.TvSuggestedShowsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTvShowsRepository(
        apiService: ApiService,
    ): TvShowsRepository {
        return TvShowsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideTvSuggestedShowsRepository(
        apiService: ApiService,
    ): TvSuggestedShowsRepository {
        return TvSuggestedShowsRepositoryImpl(apiService)
    }

}

