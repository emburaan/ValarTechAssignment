package com.valartech

import android.os.StrictMode
import androidx.multidex.MultiDexApplication
import com.valartech.di.component.DaggerApplicationComponent
import dagger.android.* import javax.inject.Inject


class MVVMApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>



    override fun onCreate() {
        super.onCreate()
        /* if (LeakCanary.isInAnalyzerProcess(this)) {
             // This process is dedicated to LeakCanary for heap analysis.
             // You should not init your app in this process.
             return
         }*/
        instance = this
        DaggerApplicationComponent.builder().application(this).build().inject(this)
        //LeakCanary.install(this)
        initStrictMode()
    }

    /**
     * You should not leave this code enabled in a production application.
     * It is designed for pre-production use only
     * Instead, a flag can be used to conditionally turn on StrictMode or off.
     */

    private fun initStrictMode() {

        if (BuildConfig.DEBUG) {

            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build())
        }
    }


    companion object {

        private lateinit var instance: MVVMApplication

        fun getInstance(): MVVMApplication = instance
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}
