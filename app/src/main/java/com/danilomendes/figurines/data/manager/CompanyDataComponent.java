package com.danilomendes.figurines.data.manager;

import com.danilomendes.figurines.di.ScreenScoped;
import com.danilomendes.figurines.ui.company_list.CompanyListFragment;

import dagger.Subcomponent;

/**
 * Created by danilo on 08-11-2017.
 */
@ScreenScoped
@Subcomponent(modules = CompanyDataModule.class)
public interface CompanyDataComponent {
    void inject(CompanyListFragment fragment);
}
