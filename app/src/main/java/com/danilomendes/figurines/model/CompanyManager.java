package com.danilomendes.figurines.model;

import android.support.annotation.VisibleForTesting;

import com.danilomendes.figurines.model.entity.Company;
import com.danilomendes.figurines.network.NetworkManager;
import com.danilomendes.figurines.utils.L;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyManager {
    private final CompanyTable mTable;
    private final NetworkManager mNetworkManager;

    @Inject
    CompanyManager(DatabaseHelper databaseHelper, NetworkManager networkManager) {
        this.mTable = databaseHelper.getCompanyTable();
        this.mNetworkManager = networkManager;
    }

    public Single<List<Company>> getAllCompanies() {
        return Single.just(mNetworkManager.isNetworkAvailable())
                .subscribeOn(Schedulers.io())
                .flatMap(isNetworkAvailable -> {
                    L.log("Is network available? " + isNetworkAvailable);
                    if (!isNetworkAvailable) {
                        return mTable.getAll();
                    }

                    return mNetworkManager.getCompaniesService().queryCompanies().doAfterSuccess(companies -> {
                        L.log("Insert data into database.");
                        mTable.insertAll(companies);
                    });
                });
    }
}
