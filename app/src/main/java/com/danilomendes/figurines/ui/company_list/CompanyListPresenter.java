package com.danilomendes.figurines.ui.company_list;

import com.danilomendes.figurines.data.CompanyManager;
import com.danilomendes.figurines.ui.base.AbstractPresenter;
import com.danilomendes.figurines.utils.L;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyListPresenter extends AbstractPresenter<CompanyListView> {
    private final CompanyManager mManager;

    @Inject
    CompanyListPresenter(CompanyManager manager) {
        this.mManager = manager;
    }

    void getCompaniesList() {
        getCompaniesList(false);
    }

    void refreshCompaniesList() {
        getCompaniesList(true);
    }

    private void getCompaniesList(boolean force) {
        showLoading();

        mCompositeDisposable.add(mManager.getAllCompanies(force)
                .observeOn(AndroidSchedulers.mainThread())
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
