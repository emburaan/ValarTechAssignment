package com.valartech.di.module

import com.valartech.global.helper.AppSchedulerProvider
import com.valartech.global.listener.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
