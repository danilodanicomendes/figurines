package com.danilomendes.figurines.data.manager

import com.danilomendes.figurines.data.entity.Company
import com.danilomendes.figurines.data.local.CompanyLocalDataSource
import com.danilomendes.figurines.data.remote.CompanyRemoteDataSource
import com.danilomendes.figurines.util.L
import com.danilomendes.figurines.util.scheduler.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by danilo on 26-11-2017.
 */
class CompanyDataManager @Inject constructor(
        private val localDataSource: CompanyLocalDataSource,
        private val companyRemoteDataSource: CompanyRemoteDataSource) : ICompanyDataManager {

    private var cachedCompanies: MutableMap<String, Company> = LinkedHashMap()

    override fun getAllCompanies(force: Boolean): Single<List<Company>> {
        if (!force && !cachedCompanies.isEmpty()) {
            return Flowable.fromIterable(cachedCompanies.values).toList()
        }

        return Single.just(companyRemoteDataSource.isNetworkAvailable())
                .subscribeOn(SchedulerProvider.io())
                .flatMap { isNetworkAvailable ->
                    if (!isNetworkAvailable) {
                        localDataSource.getAll()
                    } else {
                        companyRemoteDataSource.getCompaniesService().queryCompanies()
                                .doAfterSuccess(this::saveCompanies)
                    }
                }
    }

    private fun saveCompanies(companies: List<Company>) {
        L.log("Saving companies.")
        localDataSource.save(companies)

        L.log("Updating cache.")
        companies.associateByTo(cachedCompanies, {it.codeName}, {it})
    }
}