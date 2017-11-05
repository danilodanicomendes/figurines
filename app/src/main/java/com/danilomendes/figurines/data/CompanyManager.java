package com.danilomendes.figurines.data;

import com.danilomendes.figurines.data.entity.Company;
import com.danilomendes.figurines.data.local.CompanyLocalDataSource;
import com.danilomendes.figurines.data.remote.NetworkManager;
import com.danilomendes.figurines.utils.L;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyManager {
    private final CompanyLocalDataSource mLocalDataSource;
    private final NetworkManager mNetworkManager;

    /**
     * Cache that is filled after a remote request is done.
     */
    private Map<String, Company> mCachedCompanies;

    // TODO Change 1st param to a CompanyLocalDataSource
    @Inject
    CompanyManager(CompanyLocalDataSource localDataSource, NetworkManager networkManager) {
        this.mLocalDataSource = localDataSource;
        this.mNetworkManager = networkManager;
    }

    public Single<List<Company>> getAllCompanies(boolean force) {
        if (!force && mCachedCompanies != null) {
            return Flowable.fromIterable(mCachedCompanies.values()).toList();
        }

        return Single.just(mNetworkManager.isNetworkAvailable())
                .subscribeOn(Schedulers.io())
                .flatMap(isNetworkAvailable -> {
                    L.log("Is network available? " + isNetworkAvailable);
                    // If there's no network then fetch data from database.
                    if (!isNetworkAvailable) {
                        return mLocalDataSource.getAll()
                                .flatMap(Flowable::fromIterable).toList()
                                .doAfterSuccess(this::saveCompanies);
                    }

                    // After retrieving new data from the server store it into the database.
                    return mNetworkManager.getCompaniesService().queryCompanies()
                            .doAfterSuccess(this::saveCompanies);
                });
    }

    private void saveCompanies(List<Company> companies) {
        L.log("Saving companies.");
        mLocalDataSource.save(companies);

        if (mCachedCompanies == null) {
            mCachedCompanies = new LinkedHashMap<>(companies.size());
        }

        L.log("Updating cache.");
        for (Company company : companies) {
            mCachedCompanies.put(company.getCodeName(), company);
        }
    }
}
