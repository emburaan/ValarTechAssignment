package com.valartech.ui.home.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.valartech.R
import com.valartech.base.BaseAndroidViewModel
import com.valartech.data.model.moviemodel.Movie
import com.valartech.data.model.moviemodel.Search
import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.global.enumeration.State
import com.valartech.global.helper.Navigation
import com.valartech.global.listener.OnItemClickedListener
import com.valartech.global.listener.RetryListener
import com.valartech.global.listener.SchedulerProvider
import com.valartech.ui.home.moviedetails.MovieDetailsFragment
import com.valartech.ui.home.news.MoviesDataSource
import com.valartech.ui.home.news.MoviesDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

private const val NEWS_PAGE_SIZE = 10
private const val FIRST_PAGE = 1;

class SearchViewModel @Inject
constructor(
    application: Application,
    disposables: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private val movieRepositoryImp: MovieDetailsRepository


) : BaseAndroidViewModel(application, disposables, schedulerProvider), OnItemClickedListener,
    RetryListener {
    var movieList: LiveData<PagedList<Search>>


    var searchKeyWord: MutableLiveData<String> = MutableLiveData()

    var refreshState: MutableLiveData<State> = MutableLiveData()

    private val moviesDataSourceFactory: MoviesDataSourceFactory =
        MoviesDataSourceFactory(disposables, schedulerProvider, movieRepositoryImp, FIRST_PAGE)


    init {
        val config = PagedList.Config.Builder().setPageSize(NEWS_PAGE_SIZE)
            .setInitialLoadSizeHint(NEWS_PAGE_SIZE * 2).setEnablePlaceholders(true).build()


        movieList = LivePagedListBuilder<Int, Search>(moviesDataSourceFactory, config).build()


//        onRefresh()

    }


    private fun OnSignInSucess(): (Movie) -> Unit = { moviedetails ->


    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->
    }

    fun getState(): LiveData<State> = Transformations.switchMap<MoviesDataSource, State>(
        moviesDataSourceFactory.moviesDataSourceLiveData,
        MoviesDataSource::state
    )

    fun isEmptyLoading(): LiveData<Boolean> = Transformations.map(getState()) {
        listIsEmpty() && it == State.LOADING
    }

    fun isEmptyError(): LiveData<Boolean> = Transformations.map(getState()) {
        listIsEmpty() && it == State.ERROR
    }


    fun isRefreshing(): LiveData<Boolean> = Transformations.map(refreshState) {
        !listIsEmpty() && it == State.REFRESHING
    }

    private fun listIsEmpty(): Boolean {
        return movieList.value?.isEmpty() ?: true
    }

    override fun onItemClicked(value: String) {

        navigate(
            Navigation(
                MovieDetailsFragment::class,
                arrayOf(value, SearchViewModel::class.java)
            )
        )


    }

    override fun onRetry() {
        moviesDataSourceFactory.moviesDataSourceLiveData.value?.retry()

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun onRefresh(keyword: CharSequence?) {
        updateState(State.REFRESHING)
        if (!keyword.toString().isNotEmpty()){
            setSearchKeyword("starwars")
            shownSnackBarMessage("Search For Movie")

        }else{
            setSearchKeyword(keyword.toString())
        }

        compositeDisposable.add(
            movieRepositoryImp.getMovieList(keyword.toString(), "1fe3d02", FIRST_PAGE)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    { response ->
                        updateState(State.DONE)

                        if (!response.Response.equals("False", true)) {
                            moviesDataSourceFactory.moviesDataSourceLiveData.value?.refreshData(
                                response.Search,
                                keyword.toString()
                            )
                        }else{
                            shownSnackBarMessage("No Movie")
                        }
                    },
                    {
                        updateState(State.DONE)
                        shownSnackBarMessage(R.string.no_data)
                    }
                )
        )
    }


    private fun updateState(state: State) {
        refreshState.value = state
    }

    fun setSearchKeyword(searchkeyword: String) {
        moviesDataSourceFactory.searchkeyword.value = searchkeyword

    }


}
