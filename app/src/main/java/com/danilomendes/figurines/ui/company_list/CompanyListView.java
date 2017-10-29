package com.danilomendes.figurines.ui.company_list;

import com.danilomendes.figurines.model.entity.Company;
import com.danilomendes.figurines.ui.base.IView;

import java.util.List;

/**
 * Created by danilo on 14-10-2017.
 */
public interface CompanyListView extends IView {

    void onLoading();

    void onLoadFinished(List<Company> companies);

    void onLoadError();
}
