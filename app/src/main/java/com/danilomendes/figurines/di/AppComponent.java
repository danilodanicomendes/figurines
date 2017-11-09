package com.danilomendes.figurines.di;

import com.danilomendes.figurines.data.manager.CompanyDataComponent;
import com.danilomendes.figurines.data.manager.CompanyDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danilo on 15-10-2017.
 */
@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class, NetworkModule.class})
public interface AppComponent {
    CompanyDataComponent getCompanyComponent(CompanyDataModule companyModule);
}
