package com.yazantarifi.issuer.android.impl

interface IssuerResultBundleImplementation {

    fun setScreenResults(images: ArrayList<String>?, selectedOption: String?, userTextScreen: String?)

    fun setEmailResult(email: String?)

    fun getEmailResult(): String?

    fun getImagesPaths(): ArrayList<String>?

    fun getSelectedOption(): String?

    fun getInformation() // OS, WIFI, etc

    fun getUserInputText(): String?

}