package com.danilomendes.figurines.data.manager

import com.danilomendes.figurines.data.local.CompanyLocalDataSource
import com.danilomendes.figurines.data.local.DatabaseHelper
import com.danilomendes.figurines.data.remote.CompanyRemoteDataSource
import com.danilomendes.figurines.data.remote.NetworkManager
import com.danilomendes.figurines.di.ScreenScoped
import dagger.Module
import dagger.Provides

/**
 * Created by danilo on 07-12-2017.
 */
@Module
class CompanyDataModule {

    @Provides
    @ScreenScoped
    fun provideCompanyRemoteDataSource(networkManager: NetworkManager): CompanyRemoteDataSource {
        return CompanyRemoteDataSource(networkManager)
    }

    @Provides
    @ScreenScoped
    fun provideCompanyLocalDataSource(databaseHelper: DatabaseHelper): CompanyLocalDataSource {
        return CompanyLocalDataSource(databaseHelper)
    }

    @Provides
    @ScreenScoped
    fun provideCompanyDataSource(localDataSource: CompanyLocalDataSource, remoteDataSource: CompanyRemoteDataSource): ICompanyDataManager {
        return CompanyDataManager(localDataSource, remoteDataSource)
    }
}