package com.yazantarifi.issuer.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.IssuerConfig
import com.yazantarifi.issuer.android.data.IssuerEvents

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        IssuerConfig.sendEventName(IssuerEvents.OPTIONS_SCREEN_VIEW, arguments)
    }

}