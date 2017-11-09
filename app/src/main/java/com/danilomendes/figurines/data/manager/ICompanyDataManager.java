package com.danilomendes.figurines.data.manager;

import com.danilomendes.figurines.data.entity.Company;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by danilo on 08-11-2017.
 */
public interface ICompanyDataManager {

    Single<List<Company>> getAllCompanies(boolean force);
}
