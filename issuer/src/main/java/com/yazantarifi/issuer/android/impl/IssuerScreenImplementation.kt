package com.yazantarifi.issuer.android.impl

import android.os.Bundle

interface IssuerScreenImplementation {

    fun initScreenTitle(extras: Bundle?)

    fun setupStartScreen(extras: Bundle?)

    fun getStartFragmentId(screenMode: String): Int

    fun moveToStartScreen(screenName: Int)

    fun isEmailDialogEnabled(): Boolean

    fun showEmailDialogInput()

    fun finishScreen()

    fun getEmailExtras(key: Int): String

}