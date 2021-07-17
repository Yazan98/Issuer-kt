package com.yazantarifi.android.issuer

import android.content.Context
import android.content.Intent
import com.yazantarifi.android.issuer.data.IssueInfoType
import com.yazantarifi.android.issuer.data.IssuerOptionsInfo
import com.yazantarifi.android.issuer.data.IssuesScreenMode
import com.yazantarifi.android.issuer.impl.IssuerIntentImplementation

open class IssuerIntent constructor(private val context: Context) : Intent(), IssuerIntentImplementation {

    private var optionsListInformation: IssuerOptionsInfo? = null
    private var screensMode: IssuesScreenMode = IssuesScreenMode.DIRECT_REPORT
    private var deviceInformation: IssueInfoType = IssueInfoType.FULL
    private var isUserEmailSelectionEnabled: Boolean = false
    private var isImagesAttachmentEnabled: Boolean = false
    private var isCollectedInformationViewEnabled: Boolean = false
    private var isPrivacyPolicyEnabled: Boolean = false
    private var isEventsClickEnabled: Boolean = false
    private var privacyPolicyLink: String? = ""
    private var privacyPolicy: String? = ""
    private var screenTitle: String? = ""

    /**
     * Determine Which Type Of Information You want To Collect Inside User Report
     * Check @param type: IssueInfoType (To See The Options You Have In the Library)
     * This Option is Hidden From The User And This Information Is Collected Internally
     */
    override infix fun addDeviceInformationMode(type: IssueInfoType) {
        this.deviceInformation = type
    }

    /**
     * Determine If The Dialog Should Show At the End of The Flow or Not
     * This Dialog Simply Will Ask Users To Provide Their Email
     * So Technical Support Can Contact With Them Via The Attached Email
     */
    override infix fun isUserEmailSelectionEnabled(isEnabled: Boolean) {
        isUserEmailSelectionEnabled = isEnabled
    }

    /**
     * Decide If You want To Let Users Add Images For The Issue
     * And This Will Use Image Picker Implemented Inside System
     */
    override infix fun isImagesAttachmentEnabled(isEnabled: Boolean) {
        isImagesAttachmentEnabled = isEnabled
    }

    /**
     * This Option Will Allow You to Decide If you Want To Show Users
     * Which Information You Took From The Device and Show All of Them
     * in a Key, Value View To Let Users Know What is The Collected Information
     */
    override infix fun isCollectedInformationViewEnabled(isEnabled: Boolean) {
        isCollectedInformationViewEnabled = isEnabled
    }

    /**
     * Show Text To Let Users Read The Data Privacy or Privacy Policy
     */
    override infix fun isPrivacyPolicyEnabled(isEnabled: Boolean) {
        isPrivacyPolicyEnabled = isEnabled
    }

    override infix fun isEventsClickEnabled(isEnabled: Boolean) {
        isEventsClickEnabled = isEnabled
    }

    /**
     * Add Your Own Text To Let Users Read The Data Privacy or Privacy Policy
     */
    override infix fun addPrivacyPolicyInfo(privacyPolicy: String?) {
        this.privacyPolicy = privacyPolicy
    }

    /**
     * Add Your Own Text To Privacy Policy To Open Browser For Your Website Link
     */
    override infix fun addPrivacyPolicyLink(privacyPolicyLink: String?) {
       this.privacyPolicyLink = privacyPolicyLink
    }

    /**
     * Add Title At Toolbar
     */
    override infix fun addScreenTitle(title: String?) {
        this.screenTitle = title
    }

    /**
     * Decide What is The Best Scenario For Your Application
     * Check @param flow To See Available Screens Flow
     */
    override infix fun addScreensMode(flow: IssuesScreenMode) {
        this.screensMode = flow
    }

    /**
     * Show Options List Inside Categories List
     * This List Can Be Options Only or Title, Options
     */
    override infix fun addOptionsListInformation(options: IssuerOptionsInfo) {
        this.optionsListInformation = options
    }

    /**
     * Generate Full Intent for The Activity and Send The Arguments To Fragments
     */
    override fun build(): Intent {
        return Intent(context, IssuerScreen::class.java).apply {
            this.putExtra(IssuerConsts.IS_EMAIL_SELECTION_ENABLED, isUserEmailSelectionEnabled)
            this.putExtra(IssuerConsts.IS_IMAGE_ATTACHMENT_ENABLED, isImagesAttachmentEnabled)
            this.putExtra(IssuerConsts.IS_COLLECTED_INFORMATION_ENABLED, isCollectedInformationViewEnabled)
            this.putExtra(IssuerConsts.IS_PRIVACY_POLICY_ENABLED, isPrivacyPolicyEnabled)
            this.putExtra(IssuerConsts.IS_EVENTS_CLICK_ENABLED, isEventsClickEnabled)
            this.putExtra(IssuerConsts.PRIVACY_POLICY_LINK, privacyPolicyLink)
            this.putExtra(IssuerConsts.PRIVACY_POLICY_TEXT, privacyPolicy)
            this.putExtra(IssuerConsts.SCREEN_TITLE_TEXT, screenTitle)
            this.putExtra(IssuerConsts.DEVICE_INFORMATION_MODE, deviceInformation.key)
            this.putExtra(IssuerConsts.SCREENS_MODE, screensMode.key)
            this.putExtra(IssuerConsts.OPTIONS_LIST_INFORMATION, optionsListInformation)
        }
    }

}
