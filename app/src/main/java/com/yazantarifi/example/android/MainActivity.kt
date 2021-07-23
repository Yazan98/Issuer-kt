package com.yazantarifi.example.android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.yazantarifi.issuer.android.IssuerConsts
import com.yazantarifi.issuer.android.IssuerIntent
import com.yazantarifi.issuer.android.IssuerScreen
import com.yazantarifi.issuer.android.data.IssueInfoType
import com.yazantarifi.issuer.android.data.IssuerOption
import com.yazantarifi.issuer.android.data.IssuerResultBundle
import com.yazantarifi.issuer.android.data.IssuesScreenMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)
        val options = ArrayList<IssuerOption>().apply {
            val items = ArrayList<String>().apply {
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
                this.add("Option 1")
            }
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
            this.add(IssuerOption("Title Here", items ))
        }

        findViewById<Button>(R.id.btnClick)?.setOnClickListener {
            IssuerScreen.startScreen(this) {
                IssuerIntent addDeviceInformationMode IssueInfoType.FULL
                IssuerIntent addPrivacyPolicyInfo "Information About Your Device, and Data Policy Will Be Included In This Report, Please Specify Full Info And STR's About The Problem"
                IssuerIntent addPrivacyPolicyLink "https://www.yazantarifi.com"
                IssuerIntent addScreenTitle "Report Technical Problem"
                IssuerIntent addScreensMode IssuesScreenMode.FULL
                IssuerIntent isCollectedInformationViewEnabled true
                IssuerIntent isEventsClickEnabled true
                IssuerIntent isImagesAttachmentEnabled true
                IssuerIntent isPrivacyPolicyEnabled true
                IssuerIntent isUserEmailSelectionEnabled true
                IssuerIntent textInputHint "Brefly Explain What is The problem also Provide Us With Steps To ReProduce if Possible"
                IssuerIntent addOptionsListInformation options
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == IssuerConsts.RESULT_CODE) {
            val images: ArrayList<String>? = data?.getStringArrayListExtra(IssuerResultBundle.IMAGES_PATHS)
            val systemInfo: String? = data?.getStringExtra(IssuerResultBundle.APP_SYSTEM_INFO)
            val textIssue: String? = data?.getStringExtra(IssuerResultBundle.USER_INPUT_TEXT)

            findViewById<TextView>(R.id.textInput)?.text = "Text Input : $textIssue"
            findViewById<TextView>(R.id.imagesPaths)?.text = "Images : ${images.toString()}"
            findViewById<TextView>(R.id.systemInfo)?.text = "System Info : ${systemInfo}"
        }
    }

}
