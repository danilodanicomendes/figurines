package com.danilomendes.figurines.di

import android.app.Application
import com.danilomendes.figurines.data.remote.NetworkManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by danilo on 14-10-2017.
 */
@Module
class NetworkModule(private val mBaseUrl: String) {

    @Provides
    @Singleton
    fun provideNetworkManager(application: Application): NetworkManager {
        return NetworkManager(application, mBaseUrl)
    }
}
