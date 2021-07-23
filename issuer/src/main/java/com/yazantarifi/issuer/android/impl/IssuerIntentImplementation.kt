package com.yazantarifi.issuer.android.impl

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.yazantarifi.issuer.android.data.IssueInfoType
import com.yazantarifi.issuer.android.data.IssuerOption
import com.yazantarifi.issuer.android.data.IssuesScreenMode

interface IssuerIntentImplementation {

    fun addDeviceInformationMode(type: IssueInfoType)

    fun isUserEmailSelectionEnabled(isEnabled: Boolean)

    fun isImagesAttachmentEnabled(isEnabled: Boolean)

    fun isCollectedInformationViewEnabled(isEnabled: Boolean)

    fun isPrivacyPolicyEnabled(isEnabled: Boolean)

    fun isEventsClickEnabled(isEnabled: Boolean)

    fun addPrivacyPolicyInfo(privacyPolicy: String?)

    fun addPrivacyPolicyLink(privacyPolicyLink: String?)

    fun addEmailDialogTitle(title: String?)

    fun addEmailDialogMessage(message: String?)

    fun addScreenTitle(title: String?)

    fun addScreensMode(flow: IssuesScreenMode)

    fun addOptionsListInformation(items: ArrayList<IssuerOption>)

    fun textInputHint(hint: String)

    fun build(context: FragmentActivity?): Intent

}
