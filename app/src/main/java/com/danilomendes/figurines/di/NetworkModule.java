package com.danilomendes.figurines.di;

import android.app.Application;

import com.danilomendes.figurines.network.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danilo on 14-10-2017.
 */
@Module
public class NetworkModule {

    private final String mBaseUrl;

    public NetworkModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public NetworkManager provideNetworkManager(Application application) {
        return new NetworkManager(application, mBaseUrl);
    }
}
