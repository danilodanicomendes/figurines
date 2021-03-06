package com.danilomendes.figurines.data.manager

import com.danilomendes.figurines.di.ScreenScoped
import com.danilomendes.figurines.ui.companylist.CompanyListFragment

import dagger.Subcomponent

/**
 * Created by danilo on 08-11-2017.
 */
@ScreenScoped
@Subcomponent(modules = [(CompanyDataModule::class)])
interface CompanyDataComponent {

    fun inject(fragment: CompanyListFragment)
}
