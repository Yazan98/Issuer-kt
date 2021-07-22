package com.yazantarifi.issuer.android.impl

interface IssuerResultBundleImplementation {

    fun setImages(images: ArrayList<String>?)

    fun getImages(): ArrayList<String>?

    fun setUserTextInput(textInput: String?)

    fun getSystemInfoText(): String?

    fun setSystemTextInfo(result: String?)

    fun getUserTextInput(): String?

    fun setEmailResult(email: String?)

    fun getEmailResult(): String?

    fun getImagesPaths(): ArrayList<String>?

    fun getSelectedOption(): String?

    fun getInformation()

    fun getUserInputText(): String?

}