package com.danilomendes.figurines.ui.base

import com.danilomendes.figurines.util.L
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by danilo on 08-12-2017.
 */
abstract class AbstractPresenter<T> {

    protected var view: T? = null

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun attachView(view: T) {
        L.log("Attaching view.")
        this.view = view
    }

    fun detachView() {
        this.view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun isViewAttached(): Boolean {
        return this.view != null
    }
}