package com.danilomendes.figurines.di;

import com.danilomendes.figurines.ui.company_list.CompanyListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danilo on 15-10-2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(CompanyListFragment fragment);
}
