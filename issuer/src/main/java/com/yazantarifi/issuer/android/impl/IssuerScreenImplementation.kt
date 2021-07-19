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

    fun updateTextInput(newText: String?)

    fun showEmailDialogInput()

    fun finishScreen()

    fun getEmailExtras(key: Int): String

}