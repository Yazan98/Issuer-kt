package com.yazantarifi.issuer.android.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.IssuerConfig
import com.yazantarifi.issuer.android.IssuerConsts
import com.yazantarifi.issuer.android.IssuerScreen
import com.yazantarifi.issuer.android.adapters.OptionsAdapter
import com.yazantarifi.issuer.android.data.IssuerEvents
import com.yazantarifi.issuer.android.data.IssuerOption
import com.yazantarifi.issuer.android.data.OptionItem
import com.yazantarifi.issuer.android.impl.CategoriesFragmentImplementation
import com.yazantarifi.issuer.android.listeners.OptionSelectionListener
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : Fragment(R.layout.fragment_categories),
    CategoriesFragmentImplementation {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        IssuerConfig.sendEventName(IssuerEvents.OPTIONS_SCREEN_VIEW, arguments)
        initArguments()
    }

    override fun initArguments() {
        arguments?.let {
            (it.getParcelableArray(IssuerConsts.OPTIONS_LIST_INFORMATION) as? Array<IssuerOption>)?.let {
                setupRecyclerView(getItemsForRecyclerViewList(it))
            }
        }
    }

    override fun getItemsForRecyclerViewList(bundle: Array<IssuerOption>): ArrayList<OptionItem> {
        val items = ArrayList<OptionItem>()
        if (bundle.isNullOrEmpty()) {
            findNavController().navigate(R.id.action_categoriesFragment_to_directIssueFragment, arguments)
            return items
        }

        bundle.forEach {
            if (!TextUtils.isEmpty(it.title)) {
                items.add(OptionItem(it.title, true))
            }

            it.options?.forEach {
                items.add(OptionItem(it, false))
            }
        }

        return items
    }

    override fun setupRecyclerView(items: ArrayList<OptionItem>) {
        if (items.isEmpty()) {
            return
        }

        activity?.let {
            optionsRecyclerView?.apply {
                this.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
                this.addItemDecoration(DividerItemDecoration(it, LinearLayoutManager.VERTICAL))
                this.adapter = OptionsAdapter(items, object: OptionSelectionListener {
                    override fun onOptionSelected(option: String) {
                        (activity as? IssuerScreen)?.setSelectedOption(option)
                        findNavController().navigate(R.id.action_categoriesFragment_to_directIssueFragment, arguments)
                    }
                })
            }
        }
    }

}