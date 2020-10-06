package com.valartech.ui.home.search

import androidx.lifecycle.ViewModel
import com.valartech.di.FragmentScope
import com.valartech.di.ViewModelKey
import com.valartech.di.module.CompositeModule
import com.valartech.di.module.RepositoryModule
import com.valartech.di.module.SchedulerModule
import com.valartech.ui.home.search.adapter.MovieListAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class SearchModule {


    @IntoMap
    @Provides
    @ViewModelKey(SearchViewModel::class)
    fun bindOneViewModel(viewModel: SearchViewModel): ViewModel {
        return viewModel
    }
    @Provides
    @FragmentScope
    fun provideNewsPaginateAdapter(picasso: Picasso): MovieListAdapter {
        return MovieListAdapter(picasso)
    }


}
