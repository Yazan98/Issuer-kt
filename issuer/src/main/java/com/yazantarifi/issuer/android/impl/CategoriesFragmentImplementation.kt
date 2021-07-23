package com.yazantarifi.issuer.android.impl

import com.yazantarifi.issuer.android.data.IssuerOption
import com.yazantarifi.issuer.android.data.OptionItem

interface CategoriesFragmentImplementation {

    fun initArguments()

    fun getItemsForRecyclerViewList(items: Array<IssuerOption>): ArrayList<OptionItem>

    fun setupRecyclerView(items: ArrayList<OptionItem>)

}