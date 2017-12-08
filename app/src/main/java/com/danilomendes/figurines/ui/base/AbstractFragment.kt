package com.danilomendes.figurines.ui.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.danilomendes.figurines.FigurinesApplication
import com.danilomendes.figurines.util.L

/**
 * Created by danilo on 08-12-2017.
 */
abstract class AbstractFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isActivityAlive()) {
            injectDependencies()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ejectDependencies()
    }

    protected fun getApplication(): FigurinesApplication {
        return activity!!.application as FigurinesApplication
    }

    protected fun isActivityAlive(): Boolean {
        return activity != null && !(activity as FragmentActivity).isFinishing
    }

    @CallSuper
    protected open fun injectDependencies() {
        L.log("Injecting dependencies.")
    }

    @CallSuper
    protected open fun ejectDependencies() {
        L.log("Ejecting dependencies.")
    }

    @StringRes abstract fun getToolbarTitle(): Int
}