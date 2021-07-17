package com.yazantarifi.android.issuer.impl

interface IssuerListener {

    fun onErrorTriggered(error: Throwable)

    fun onEventClickTriggered(eventName: String)

}
