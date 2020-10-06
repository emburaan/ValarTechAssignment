package com.valartech.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.valartech.data.model.moviemodel.Search
import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.global.enumeration.State
import com.valartech.global.listener.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action


class MoviesDataSource(
    private val searchkeyword: MutableLiveData<String>,
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val moviesRepository: MovieDetailsRepository,
    private val firstPage: Int
) : PageKeyedDataSource<Int, Search>() {

    var state: MutableLiveData<State> = MutableLiveData()


    private var retryCompletable: Completable? = null

    private var responseRefresh: List<Search>? = null;

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Search>
    ) {
        responseRefresh?.let {
            fetchRefresh(callback, it);
        } ?: run {

            fetchLoadInitial(params, callback)
        }
    }

    private fun fetchLoadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Search>
    ): Boolean {
        updateState(State.LOADING)
        return compositeDisposable.add(
            moviesRepository.getMovieList(searchkeyword.value.toString(), "1fe3d02", firstPage)
                .flatMap {
                    Observable.fromIterable(it.Search)
                        .map {
                            Search(it.Poster, it.Title, it.Type, it.Year, it.imdbID)
                        }.toList()
                }.subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response,
                            null,
                            firstPage + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    private fun fetchRefresh(
        callback: LoadInitialCallback<Int, Search>,
        it: List<Search>
    ) {

        callback.onResult(
            it,
            null,
            firstPage + 1
        )
        responseRefresh = null
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Search>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            moviesRepository.getMovieList(searchkeyword.value.toString(), "1fe3d02", params.key)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(
                            response.Search,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Search>) {
    }

    private fun updateState(newState: State) {
        state.postValue(newState)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }


    fun refreshData(response: List<Search>, keyword: String) {
        responseRefresh = response
        invalidate()
    }


}