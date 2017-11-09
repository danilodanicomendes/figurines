package com.danilomendes.figurines.ui.company_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.danilomendes.figurines.R;
import com.danilomendes.figurines.data.entity.Company;
import com.danilomendes.figurines.ui.base.AbstractFragment;
import com.danilomendes.figurines.ui.utils.views.CustomItemDecoration;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by danilo on 14-10-2017.
 */
public class CompanyListFragment extends AbstractFragment implements ICompanyListView {

    @Inject CompanyListPresenter mCompanyListPresenter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_company_list, container, false);

        mProgressBar = root.findViewById(R.id.pb_load);
        mRecyclerView = root.findViewById(R.id.rv_company_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new CustomItemDecoration(getContext()
                .getResources().getDimensionPixelSize(R.dimen.list_item_divider_height)));

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mCompanyListPresenter.attachView(this);
        mCompanyListPresenter.getCompaniesList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompanyListPresenter.detachView();
    }

    @Override
    public void onLoading() {
        Toast.makeText(getContext(), "Loading....", Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinished(List<Company> companies) {
        Toast.makeText(getContext(), "Success: " + Arrays.toString(companies.toArray()), Toast.LENGTH_LONG).show();
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new CompanyListAdapter(companies));
    }

    @Override
    public void onLoadError() {
        Toast.makeText(getContext(), "Error.....", Toast.LENGTH_LONG).show();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        getApplication().getCompanyComponent().inject(this);
    }

    @Override
    protected void ejectDependencies() {
        super.ejectDependencies();
        getApplication().clearCompanyComponent();
    }

    @Override
    public int getToolbarTitle() {
        return R.string.fragment_comany_list;
    }
}
