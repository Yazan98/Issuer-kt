# Issuer-kt
Android Library Built To Create Report Screen In Android Apps

### The Problem
The Problem is Each Time you want to Track Device Info you should Ask Users To Provide Their Device Info but In This Screen the Screen Will Collect Device, System Information without Asking Users Too Much Info About The Device

## Installation
```
dependencies {
    implementation "com.yazantarifi:issuer.android:1.0.1"
}
```

## Usage Example

1. Start The Screen
```
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
```

2. Return Results
```
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
```

> See Full Example Inside App Module

Light Mode             |  Dark Mode
:-------------------------:|:-------------------------:
![Screenshot_20210723-034805_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726329-ff26d23d-590a-48f0-8a2e-9fd9450b1ad0.jpg)  | ![Screenshot_20210723-034738_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726390-2f8858be-80d9-472c-98a9-8aff080a39b9.jpg)
![Screenshot_20210723-034813_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726338-990ae7a4-1198-4b3c-aa7c-1eef52e756a1.jpg) |  ![Screenshot_20210723-034747_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726421-dadb86b3-7520-4ee0-b21a-f702ff042989.jpg)
![Screenshot_20210723-034817_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726351-2eacfadd-7fb4-4f49-8dd0-6289eb7b3f48.jpg)  |  ![Screenshot_20210723-034751_Issuer-kt](https://user-images.githubusercontent.com/29167110/126726441-89fef406-0af9-4cd0-8793-6c1cdf06b3cc.jpg)






