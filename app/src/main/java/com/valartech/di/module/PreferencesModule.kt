package com.valartech.di.module


import android.content.Context
import com.valartech.di.ApplicationContext
import com.valartech.di.ApplicationScope
import com.valartech.global.helper.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class, ParsingModule::class])
class PreferencesModule {

    @Provides
    @ApplicationScope
    fun sharedPreferences(@ApplicationContext context: Context, moshi: Moshi): SharedPreferences {
        return SharedPreferences(context, moshi)
    }
}
