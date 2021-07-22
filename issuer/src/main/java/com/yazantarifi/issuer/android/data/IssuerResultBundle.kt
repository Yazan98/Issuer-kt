package com.yazantarifi.issuer.android.data

import android.content.Intent
import com.yazantarifi.issuer.android.impl.IssuerResultBundleImplementation

class IssuerResultBundle : Intent(), IssuerResultBundleImplementation {

    companion object {
        const val IMAGES_PATHS = "extras.images.paths"
        const val EMAIL_PATH = "extras.email"
        const val SELECTED_OPTION = "extras.option"
        const val APP_SYSTEM_INFO = "extras.info"
        const val USER_INPUT_TEXT = "extras.text"
    }

    override fun setSystemTextInfo(result: String?) {
        this.putExtra(APP_SYSTEM_INFO, result)
    }

    override fun getSystemInfoText(): String? {
        return this.getStringExtra(APP_SYSTEM_INFO)
    }

    override fun setImages(images: ArrayList<String>?) {
        this.putStringArrayListExtra(IMAGES_PATHS, images)
    }

    override fun getImages(): ArrayList<String>? {
        return this.getStringArrayListExtra(IMAGES_PATHS)
    }

    override fun setUserTextInput(textInput: String?) {
        this.putExtra(USER_INPUT_TEXT, textInput)
    }

    override fun getUserTextInput(): String? {
        return this.getStringExtra(USER_INPUT_TEXT)
    }

    override fun setEmailResult(email: String?) {
        this.putExtra(EMAIL_PATH, email)
    }

    override fun getEmailResult(): String? {
        return this.getStringExtra(EMAIL_PATH)
    }

    override fun getImagesPaths(): ArrayList<String>? {
        TODO("Not yet implemented")
    }

    override fun getSelectedOption(): String? {
        TODO("Not yet implemented")
    }

    override fun getInformation() {
        TODO("Not yet implemented")
    }

    override fun getUserInputText(): String? {
        TODO("Not yet implemented")
    }

}