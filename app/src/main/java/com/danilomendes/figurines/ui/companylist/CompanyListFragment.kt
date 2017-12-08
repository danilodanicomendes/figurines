package com.danilomendes.figurines.ui.companylist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.danilomendes.figurines.R
import com.danilomendes.figurines.data.entity.Company
import com.danilomendes.figurines.ui.base.AbstractFragment
import com.danilomendes.figurines.ui.utils.views.CustomItemDecoration
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_company_list.*
import kotlinx.android.synthetic.main.holder_company_list_item.view.*

import java.util.*
import javax.inject.Inject

/**
 * Created by danilo on 08-12-2017.
 */
class CompanyListFragment : AbstractFragment(), ICompanyListView {

    @Inject lateinit var companyListPresenter: CompanyListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_company_list, container, false)
    }

    /**
     * Kotlin synthetic basically calls getView() and the caches it. For this reason,
     * the variable access can only be done after the view is created.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_company_list.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        rv_company_list.addItemDecoration(CustomItemDecoration(context!!
                .resources.getDimensionPixelSize(R.dimen.list_item_divider_height)))

        companyListPresenter.attachView(this)
        companyListPresenter.getCompaniesList()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        companyListPresenter.detachView()
    }

    override fun onLoading() {
        Toast.makeText(context, "Loading....", Toast.LENGTH_SHORT).show()
        pb_load.visibility = View.VISIBLE
    }

    override fun onLoadFinished(companies: List<Company>) {
        Toast.makeText(context, "Success: " + Arrays.toString(companies.toTypedArray()), Toast.LENGTH_LONG).show()
        pb_load.visibility = View.GONE
        rv_company_list.adapter = CompanyListAdapter(companies)
    }

    override fun onLoadError() {
        Toast.makeText(context, "Error.....", Toast.LENGTH_LONG).show()
        pb_load.visibility = View.GONE
    }

    override fun injectDependencies() {
        super.injectDependencies()
        getApplication().companyComponent!!.inject(this)
    }

    override fun ejectDependencies() {
        super.ejectDependencies()
        getApplication().clearCompanyComponent()
    }

    override fun getToolbarTitle() = R.string.fragment_comany_list
}

private class CompanyListAdapter(private val companyList: List<Company>)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setupViewHolder(companyList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.holder_company_list_item, parent, false)

        return ViewHolder(view)
    }
}

private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.tv_name
    private val logo = itemView.iv_logo
    private val shortDescription = itemView.tv_short_description

    fun setupViewHolder(company: Company) {
        title.text = company.name
        Picasso.with(logo.context).load(company.logo).into(logo)
        shortDescription.text = company.shortDescription
    }
}