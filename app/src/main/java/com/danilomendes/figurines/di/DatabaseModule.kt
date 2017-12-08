package com.danilomendes.figurines.di

import android.app.Application
import com.danilomendes.figurines.data.local.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danilo on 08-11-2017.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabaseHelper(application: Application): DatabaseHelper {
        return DatabaseHelper(application)
    }
}
