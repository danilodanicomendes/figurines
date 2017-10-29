package com.danilomendes.figurines.ui.company_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danilomendes.figurines.R;
import com.danilomendes.figurines.model.entity.Company;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by danilo on 22-10-2017.
 */
class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {

    private final List<Company> mCompanyList;

    CompanyListAdapter(@NonNull List<Company> companyList) {
        this.mCompanyList = companyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.holder_company_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setupViewHolder(mCompanyList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView mLogo;

        ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_name);
            mLogo = itemView.findViewById(R.id.iv_logo);
        }

        void setupViewHolder(Company company) {
            mTitle.setText(company.getName());
            Picasso.with(mLogo.getContext()).load(company.getLogo()).into(mLogo);
        }
    }
}
