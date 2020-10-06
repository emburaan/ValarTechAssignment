package com.valartech.di.module


import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.data.repository.imp.MovieRepositoryImp

import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {



    @Binds
    abstract fun provideMovieRepository(timerImp: MovieRepositoryImp): MovieDetailsRepository



}
