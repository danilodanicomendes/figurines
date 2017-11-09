package com.danilomendes.figurines.data.remote;

import com.danilomendes.figurines.data.remote.api.CompanyService;

import javax.inject.Inject;

/**
 * Created by danilo on 08-11-2017.
 */
public class CompanyRemoteDataSource {
    private final NetworkManager mNetworkManager;


    @Inject
    public CompanyRemoteDataSource(NetworkManager networkManager) {
        this.mNetworkManager = networkManager;
    }

    public CompanyService getCompaniesService() {
        return mNetworkManager.getRetrofit().create(CompanyService.class);
    }

    public boolean isNetworkAvailable() {
        return mNetworkManager.isNetworkAvailable();
    }
}
