package com.valartech.di.module


import android.content.Context
import com.valartech.di.ApplicationContext
import com.valartech.di.ApplicationScope
import com.valartech.data.db.Database
import com.valartech.data.db.DatabaseBuilder
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun databaseProvider(@ApplicationContext context: Context): Database {
        return DatabaseBuilder.getBingoDatabase(context)
    }
}
