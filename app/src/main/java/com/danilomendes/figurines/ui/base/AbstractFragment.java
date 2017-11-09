package com.danilomendes.figurines.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.danilomendes.figurines.FigurinesApplication;
import com.danilomendes.figurines.util.L;

/**
 * Created by danilo on 14-10-2017.
 */
public abstract class AbstractFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isActivityAlive()) {
            injectDependencies();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ejectDependencies();
    }

    protected FigurinesApplication getApplication() {
        return (FigurinesApplication) getActivity().getApplicationContext();
    }

    protected boolean isActivityAlive() {
        return getActivity() != null && !getActivity().isFinishing();
    }

    @CallSuper
    protected void injectDependencies() {
        L.log("Injecting dependencies.");
    }

    @CallSuper
    protected void ejectDependencies() {
        L.log("Ejecting dependencies.");
    }

    public abstract @StringRes int getToolbarTitle();
}
