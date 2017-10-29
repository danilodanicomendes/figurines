package com.danilomendes.figurines.di;

import android.app.Application;

import com.danilomendes.figurines.R;
import com.danilomendes.figurines.utils.L;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danilo on 14-10-2017.
 */
@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
