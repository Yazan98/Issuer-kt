package com.yazantarifi.example.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yazantarifi.issuer.android.IssuerIntent
import com.yazantarifi.issuer.android.IssuerScreen
import com.yazantarifi.issuer.android.data.IssueInfoType
import com.yazantarifi.issuer.android.data.IssuesScreenMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)

        findViewById<Button>(R.id.btnClick)?.setOnClickListener {
            IssuerScreen.startScreen(this) {
                IssuerIntent addDeviceInformationMode IssueInfoType.FULL
                IssuerIntent addPrivacyPolicyInfo "Data Policy"
                IssuerIntent addPrivacyPolicyLink "https://www.yazantarifi.com"
                IssuerIntent addScreenTitle "Report Technical Problem"
                IssuerIntent addScreensMode IssuesScreenMode.DIRECT_REPORT
                IssuerIntent isCollectedInformationViewEnabled true
                IssuerIntent isEventsClickEnabled true
                IssuerIntent isImagesAttachmentEnabled true
                IssuerIntent isPrivacyPolicyEnabled true
                IssuerIntent isUserEmailSelectionEnabled true
                IssuerIntent textInputHint "Brefly Explain What is The problem also Provide Us With Steps To ReProduce if Possible"
            }
        }
    }

}
