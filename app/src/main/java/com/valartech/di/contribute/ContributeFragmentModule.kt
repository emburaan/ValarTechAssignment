package com.valartech.di.contribute

import com.valartech.di.FragmentScope
import com.valartech.ui.home.search.SearchFragment
import com.valartech.ui.home.search.SearchModule
import com.valartech.ui.home.moviedetails.MovieDetailsFragment
import com.valartech.ui.home.moviedetails.MovieModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributeFragmentModule {


    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun contributeOneFragment(): SearchFragment



    @FragmentScope
    @ContributesAndroidInjector(modules = [MovieModule::class])
    abstract fun contributeFourFragment(): MovieDetailsFragment

}