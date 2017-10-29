package com.danilomendes.figurines.network.api;

import com.danilomendes.figurines.model.entity.Company;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by danilo on 11-10-2017.
 */
public interface CompanyService {

    @GET("companies.json")
    Single<List<Company>> queryCompanies();
}
