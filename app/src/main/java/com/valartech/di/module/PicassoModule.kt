package com.valartech.di.module

import android.content.Context
import com.valartech.di.ApplicationContext
import com.valartech.di.ApplicationScope
import com.valartech.di.PicassoClientScope
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = [ApplicationModule::class, NetworkModule::class])
class PicassoModule {

    @Provides
    @ApplicationScope
    fun picasso(@ApplicationContext context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context).loggingEnabled(true).downloader(okHttp3Downloader).build()
    }

    @Provides
    @ApplicationScope
    fun okHttp3Downloader(@PicassoClientScope okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

}
