package com.danilomendes.figurines.data.manager

import com.danilomendes.figurines.data.entity.Company

import io.reactivex.Single

/**
 * Created by danilo on 08-11-2017.
 */
interface ICompanyDataManager {

    fun getAllCompanies(force: Boolean): Single<List<Company>>
}
