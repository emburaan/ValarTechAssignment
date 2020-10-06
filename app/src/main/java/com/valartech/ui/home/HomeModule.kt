package com.valartech.ui.home

import androidx.lifecycle.ViewModel
import com.valartech.di.ViewModelKey
import com.valartech.di.module.CompositeModule
import com.valartech.di.module.RepositoryModule
import com.valartech.di.module.SchedulerModule
import com.valartech.data.model.user.User
import com.valartech.global.utils.ExtraKeys
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named


@Module(includes = [RepositoryModule::class, CompositeModule::class, SchedulerModule::class])
class HomeModule {


    @IntoMap
    @Provides
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel {
        return homeViewModel
    }


    @Provides
    @Named(ExtraKeys.HomeActivity.HOME_INJECT_USER_KEY)
    fun provideUser(mainActivity: HomeActivity): User {
        return mainActivity.intent.getParcelableExtra(ExtraKeys.HomeActivity.HOME_EXTRA_USER_KEY)
    }
}
