package com.valartech.ui.home.moviedetails

import androidx.lifecycle.ViewModel
import com.valartech.di.ViewModelKey
import com.valartech.di.module.CompositeModule
import com.valartech.di.module.RepositoryModule
import com.valartech.di.module.SchedulerModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class MovieModule {

    @IntoMap
    @Provides
    @ViewModelKey(MovieDetailsViewModel::class)
    fun bindThreeViewModel(viewModel: MovieDetailsViewModel): ViewModel {
        return viewModel
    }

}
