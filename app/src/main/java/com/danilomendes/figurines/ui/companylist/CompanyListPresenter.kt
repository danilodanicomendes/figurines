package com.danilomendes.figurines.ui.companylist

import com.danilomendes.figurines.data.manager.ICompanyDataManager
import com.danilomendes.figurines.ui.base.AbstractPresenter
import com.danilomendes.figurines.util.L
import com.danilomendes.figurines.util.scheduler.SchedulerProvider
import javax.inject.Inject

/**
 * Created by danilo on 08-12-2017.
 */
class CompanyListPresenter @Inject constructor(
        private val companyDataManager: ICompanyDataManager)
    : AbstractPresenter<ICompanyListView>() {

    fun getCompaniesList(force: Boolean = false) {
        showLoading()

        compositeDisposable.add(companyDataManager.getAllCompanies(force)
                .observeOn(SchedulerProvider.ui())
                .subscribe({
                    L.log("onSuccess")
                    if (isViewAttached()) {
                        if (!it.isEmpty()) view?.onLoadFinished(it) else view?.onLoadError()
                    }
                }, {
                    L.log("onError")
                    if (isViewAttached()) {
                        L.log("Error", it)
                        view?.onLoadError()
                    }
                }))
    }

    private fun showLoading() {
        L.log("showLoading")
        if (isViewAttached()) {
            view!!.onLoading()
        }
    }
}