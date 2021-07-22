package com.yazantarifi.issuer.android.impl

interface IssuerApplicationInformationImplementation {

    fun getAndroidVersion(): String

    fun getDeviceName(): String

    fun getDeviceId(): String

    fun capitalize(s: String?): String

    fun getDeviceInfo(): String

    fun getApplicationName(): String

}