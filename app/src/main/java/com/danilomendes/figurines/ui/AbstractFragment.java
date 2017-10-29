package com.danilomendes.figurines.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danilomendes.figurines.FigurinesApplication;

/**
 * Created by danilo on 14-10-2017.
 */
public class AbstractFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isActivityAlive()) {
            injectDependencies();
        }
    }

    protected FigurinesApplication getApplication() {
        return (FigurinesApplication) getActivity().getApplicationContext();
    }

    protected boolean isActivityAlive() {
        return getActivity() != null && !getActivity().isFinishing();
    }

    protected void injectDependencies() {

    }
}
