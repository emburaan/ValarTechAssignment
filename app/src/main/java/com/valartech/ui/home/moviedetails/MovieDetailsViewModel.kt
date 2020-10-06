package com.valartech.ui.home.moviedetails

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.valartech.base.BaseAndroidViewModel
import com.valartech.data.model.moviedetailsmodel.MovieDetails
import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.global.listener.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieDetailsViewModel @Inject
constructor(
    application: Application,
    disposables: CompositeDisposable,
    schedulerProvider: SchedulerProvider,
    private val movieDetailsRepository: MovieDetailsRepository
    /*  @Named(ExtraKeys.FourFragment.FOUR_INJECT_ARG1_KEY) arg1: String,
      @Named(ExtraKeys.FourFragment.FOUR_INJECT_ARG2_KEY) arg2: String*/

) : BaseAndroidViewModel(application, disposables, schedulerProvider) {

    var moviedetailsdata:MutableLiveData<MovieDetails> = MutableLiveData()

    val result: MutableLiveData<String> = MutableLiveData()

    init {

    }

    fun getMovieDetails(id:String,apikey:String) {
        showBlockProgressBar()
        compositeDisposable.add(
            movieDetailsRepository.getMovieDetails(id,apikey)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(OnSignInSucess(), OnSignInFail())
        )


    }

    private fun OnSignInSucess(): (MovieDetails) -> Unit = { moviedetails ->
        hideBlockProgressBar()
        moviedetailsdata.value = moviedetails



    }

    private fun OnSignInFail(): (Throwable) -> Unit = { error ->
        shownSnackBarMessage(error.message.toString())
    }


}
