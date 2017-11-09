package com.danilomendes.figurines.di;

import android.app.Application;

import com.danilomendes.figurines.data.local.DatabaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danilo on 08-11-2017.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Application application) {
        return new DatabaseHelper(application);
    }
}
