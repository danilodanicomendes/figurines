package com.danilomendes.figurines.data.remote

import com.danilomendes.figurines.data.remote.api.CompanyService
import javax.inject.Inject

/**
 * Created by danilo on 08-12-2017.
 */
class CompanyRemoteDataSource @Inject constructor(
        private val networkManager: NetworkManager) {

    fun getCompaniesService(): CompanyService {
        return networkManager.retrofit.create(CompanyService::class.java)
    }

    fun isNetworkAvailable(): Boolean {
        return networkManager.isNetworkAvailable()
    }
}