package com.valartech.di.contribute

import com.valartech.di.ActivityScope
import com.valartech.ui.home.HomeActivity
import com.valartech.ui.home.HomeModule


import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeActivityModule {


    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class, ContributeFragmentModule::class])
    abstract fun contributeHomeActivity(): HomeActivity



}
