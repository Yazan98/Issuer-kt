package com.yazantarifi.android.issuer.impl

import android.content.Intent
import com.yazantarifi.android.issuer.data.IssueInfoType
import com.yazantarifi.android.issuer.data.IssuerOptionsInfo
import com.yazantarifi.android.issuer.data.IssuesScreenMode

interface IssuerIntentImplementation {

    fun addDeviceInformationMode(type: IssueInfoType)

    fun isUserEmailSelectionEnabled(isEnabled: Boolean)

    fun isImagesAttachmentEnabled(isEnabled: Boolean)

    fun isCollectedInformationViewEnabled(isEnabled: Boolean)

    fun isPrivacyPolicyEnabled(isEnabled: Boolean)

    fun isEventsClickEnabled(isEnabled: Boolean)

    fun addPrivacyPolicyInfo(privacyPolicy: String?)

    fun addPrivacyPolicyLink(privacyPolicyLink: String?)

    fun addScreenTitle(title: String?)

    fun addScreensMode(flow: IssuesScreenMode)

    fun addOptionsListInformation(options: IssuerOptionsInfo)

    fun build(): Intent

}
