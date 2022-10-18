package com.compose.movies.di


import com.compose.movies.domain.repositories.TvShowsRepository
import com.compose.movies.domain.repositories.TvShowsRepositoryImpl
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
    ): TvShowsRepository {
        return TvShowsRepositoryImpl()
    }

}

