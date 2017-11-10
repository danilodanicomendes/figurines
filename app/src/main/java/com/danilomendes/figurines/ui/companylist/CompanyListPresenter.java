package com.danilomendes.figurines.ui.companylist;

import com.danilomendes.figurines.data.manager.ICompanyDataManager;
import com.danilomendes.figurines.ui.base.AbstractPresenter;
import com.danilomendes.figurines.util.L;
import com.danilomendes.figurines.util.scheduler.SchedulerProvider;

import javax.inject.Inject;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyListPresenter extends AbstractPresenter<ICompanyListView> {
    private final ICompanyDataManager mCompanyDataSource;

    @Inject
    CompanyListPresenter(ICompanyDataManager manager) {
        this.mCompanyDataSource = manager;
    }

    void getCompaniesList() {
        getCompaniesList(false);
    }

    void refreshCompaniesList() {
        getCompaniesList(true);
    }

    private void getCompaniesList(boolean force) {
        showLoading();

        mCompositeDisposable.add(mCompanyDataSource.getAllCompanies(force)
                .observeOn(SchedulerProvider.Companion.ui())
                .subscribe(o -> {
                    L.log("onSuccess");
                    if (isViewAttached()) {
                        if (o.isEmpty()) {
                            mView.onLoadError();
                        } else {
                            mView.onLoadFinished(o);
                        }
                    }
                }, throwable -> {
                    L.log("onError");
                    if (isViewAttached()) {
                        L.log("Error", throwable);
                        mView.onLoadError();
                    }
                }));
    }

    private void showLoading() {
        L.log("showLoading");
        if (isViewAttached()) {
            mView.onLoading();
        }
    }
}
