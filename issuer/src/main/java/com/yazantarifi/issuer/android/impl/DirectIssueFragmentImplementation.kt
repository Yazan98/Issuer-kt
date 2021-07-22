package com.yazantarifi.issuer.android.impl

import android.os.Bundle

interface DirectIssueFragmentImplementation {

    fun initArguments()

    fun setupImagesRecyclerView()

    fun pickImagesFromGallery()

    fun setupViewsListeners()

    fun initCollectionInfo(arguments: Bundle)

    fun openWebBrowser(link: String?)

}