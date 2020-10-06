package com.valartech.di.component


import android.app.Application
import com.valartech.MVVMApplication
import com.valartech.di.ApplicationScope
import com.valartech.di.contribute.ContributeActivityModule
import com.valartech.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ContributeActivityModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance

        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MVVMApplication)

}
