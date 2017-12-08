package com.danilomendes.figurines.data.remote.api

import com.danilomendes.figurines.data.entity.Company
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by danilo on 07-12-2017.
 */
interface CompanyService {

    @GET("companies.json")
    fun queryCompanies(): Single<List<Company>>
}