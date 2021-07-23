package com.yazantarifi.issuer.android.impl

import android.os.Bundle
import androidx.navigation.NavGraph

interface IssuerScreenImplementation {

    fun initScreenTitle(extras: Bundle?)

    fun setupStartScreen(extras: Bundle?)

    fun getStartFragmentId(screenMode: String): Int

    fun moveToStartScreen(screenName: Int)

    fun addStartFragmentArguments(graph: NavGraph?)

    fun isEmailDialogEnabled(): Boolean

    fun setImages(items: ArrayList<String>?)

    fun setSystemTextInfo(text: String?)

    fun updateTextInput(newText: String?)

    fun showEmailDialogInput()

    fun setSelectedOption(option: String?)

    fun getEmailExtras(key: Int): String

}