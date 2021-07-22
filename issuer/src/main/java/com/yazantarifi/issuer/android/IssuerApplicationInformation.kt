package com.yazantarifi.issuer.android

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import com.yazantarifi.issuer.android.impl.IssuerApplicationInformationImplementation
import android.provider.Settings

class IssuerApplicationInformation constructor(
    private val context: Context
): IssuerApplicationInformationImplementation {
    override fun getAndroidVersion(): String {
        val release = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT
        return " $sdkVersion ($release)"
    }

    override fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }

    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    override fun capitalize(s: String?): String {
        if (s == null || s.isEmpty()) {
            return ""
        }
        val first = s[0]
        return if (Character.isUpperCase(first)) {
            s
        } else {
            Character.toUpperCase(first) + s.substring(1)
        }
    }

    override fun getDeviceInfo(): String {
        var s = ""
        s += "OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")"
        s += "\n OS API Level: ${Build.VERSION.SDK_INT}"
        s += "\n Device: " + Build.DEVICE
        s += "\n Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")"
        return s
    }

    override fun getApplicationName(): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }

    override fun getFullTextInfo(): String {
        var s = ""
        s += "Device ID: " + "(" + getDeviceId() + ")"
        s += "\n Device Brand: " + "(" + Build.BRAND + ")"
        s += "\n Device Manufacture: " + "(" + Build.MANUFACTURER + ")"
        s += "\n Android Version: " + "(" + getAndroidVersion() + ")"
        s += "\n OS Version: " + System.getProperty("os.version") + "(" + Build.VERSION.INCREMENTAL + ")"
        s += "\n OS API Level: ${Build.VERSION.SDK_INT}"
        s += "\n Device: " + Build.DEVICE
        s += "\n Model (and Product): " + Build.MODEL + " (" + Build.PRODUCT + ")"
        s += "\n Device Hardware: " + Build.HARDWARE
        s += "\n Device Release Version: " + Build.VERSION.RELEASE
        s += "\n Network: " + getNetworkClass(context)
        return s
    }

}
