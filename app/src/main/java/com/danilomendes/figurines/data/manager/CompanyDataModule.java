package com.danilomendes.figurines.data.manager;

import com.danilomendes.figurines.data.local.CompanyLocalDataSource;
import com.danilomendes.figurines.data.local.DatabaseHelper;
import com.danilomendes.figurines.data.remote.CompanyRemoteDataSource;
import com.danilomendes.figurines.data.remote.NetworkManager;
import com.danilomendes.figurines.di.ScreenScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danilo on 08-11-2017.
 */
@Module
public class CompanyDataModule {

    @Provides
    @ScreenScoped
    CompanyRemoteDataSource provideCompanyRemoteDataSource(NetworkManager networkManager) {
        return new CompanyRemoteDataSource(networkManager);
    }

    @Provides
    @ScreenScoped
    CompanyLocalDataSource provideCompanyLocalDataSource(DatabaseHelper databaseHelper) {
        return new CompanyLocalDataSource(databaseHelper);
    }

    @Provides
    @ScreenScoped
    ICompanyDataManager providesCompanyDataSource(CompanyLocalDataSource localDataSource, CompanyRemoteDataSource remoteDataSource) {
        return new CompanyDataManager(localDataSource, remoteDataSource);
    }
}
