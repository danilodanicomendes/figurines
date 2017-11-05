package com.danilomendes.figurines.ui.base;

import android.support.annotation.NonNull;

import com.danilomendes.figurines.utils.L;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by danilo on 18-10-2017.
 */
public abstract class AbstractPresenter<T> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    public final void attachView(@NonNull T view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    public final void detachView() {
        mView = null;
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            L.log("Dispose disposables.");
            mCompositeDisposable.dispose();
        }
    }

    protected final boolean isViewAttached() {
        return mView != null;
    }
}
