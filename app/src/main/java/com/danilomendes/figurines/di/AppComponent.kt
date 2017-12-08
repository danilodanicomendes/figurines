package com.danilomendes.figurines.di

import com.danilomendes.figurines.data.manager.CompanyDataComponent
import com.danilomendes.figurines.data.manager.CompanyDataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by danilo on 15-10-2017.
 */
@Singleton
@Component(modules = [(AppModule::class), (DatabaseModule::class), (NetworkModule::class)])
interface AppComponent {
    fun getCompanyComponent(companyModule: CompanyDataModule): CompanyDataComponent
}
