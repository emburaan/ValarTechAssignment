package com.valartech.ui.home

import android.app.Application
import com.valartech.base.BaseAndroidViewModel
import com.valartech.global.listener.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject
constructor(
        application: Application,
        disposables: CompositeDisposable,
        schedulerProvider: SchedulerProvider
) :
        BaseAndroidViewModel(application, disposables, schedulerProvider) {

    init {
        //shownSnackBarMessage("${applicationContext.getString(R.string.home_hello)}${user.email}")
    }


    fun onActionOneClicked() {
       // navigate(Navigation(OneFragment::class, arrayOf(user)))
    }

    fun onActionTwoClicked() {
    }


}
