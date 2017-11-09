package com.danilomendes.figurines.data.manager;

import com.danilomendes.figurines.data.entity.Company;
import com.danilomendes.figurines.data.local.CompanyLocalDataSource;
import com.danilomendes.figurines.data.remote.CompanyRemoteDataSource;
import com.danilomendes.figurines.util.L;
import com.danilomendes.figurines.util.scheduler.SchedulerProvider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyDataManager implements ICompanyDataManager {
    private final CompanyLocalDataSource mLocalDataSource;
    private final CompanyRemoteDataSource mCompanyRemoteDataSource;

    /**
     * Cache that is filled after a remote request is done.
     */
    private Map<String, Company> mCachedCompanies;

    @Inject
    public CompanyDataManager(CompanyLocalDataSource localDataSource, CompanyRemoteDataSource companyRemoteDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mCompanyRemoteDataSource = companyRemoteDataSource;
    }

    @Override
    public Single<List<Company>> getAllCompanies(boolean force) {
        if (!force && mCachedCompanies != null) {
            return Flowable.fromIterable(mCachedCompanies.values()).toList();
        }

        return Single.just(mCompanyRemoteDataSource.isNetworkAvailable())
                .subscribeOn(SchedulerProvider.Companion.io())
                .flatMap(isNetworkAvailable -> {
                    L.log("Is network available? " + isNetworkAvailable);
                    // If there's no network then fetch data from database.
                    if (!isNetworkAvailable) {
                        return mLocalDataSource.getAll()
                                .flatMap(Flowable::fromIterable).toList()
                                .doAfterSuccess(this::saveCompanies);
                    }

                    // After retrieving new data from the server store it into the database.
                    return mCompanyRemoteDataSource.getCompaniesService().queryCompanies()
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
