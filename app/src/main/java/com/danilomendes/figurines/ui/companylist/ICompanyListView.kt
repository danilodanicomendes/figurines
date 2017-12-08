package com.danilomendes.figurines.ui.companylist

import com.danilomendes.figurines.data.entity.Company
import com.danilomendes.figurines.ui.base.IView

/**
 * Created by danilo on 14-10-2017.
 */
interface ICompanyListView : IView {

    fun onLoading()

    fun onLoadFinished(companies: List<Company>)

    fun onLoadError()
}
