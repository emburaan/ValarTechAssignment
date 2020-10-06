package com.valartech.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.valartech.data.model.moviemodel.Search
import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.global.listener.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class MoviesDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val movieRepository: MovieDetailsRepository,
    private val firstPage: Int
) : DataSource.Factory<Int, Search>() {


    val moviesDataSourceLiveData = MutableLiveData<MoviesDataSource>()
    val searchkeyword = MutableLiveData<String>()


    override fun create(): DataSource<Int, Search> {
        val newsDataSource = MoviesDataSource(
            searchkeyword,
            compositeDisposable,
            schedulerProvider,
            movieRepository,
            firstPage
        )
        moviesDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}