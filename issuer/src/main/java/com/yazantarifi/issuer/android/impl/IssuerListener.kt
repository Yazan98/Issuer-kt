package com.yazantarifi.issuer.android.impl

interface IssuerListener {

    fun onErrorTriggered(error: Throwable)

    fun onEventClickTriggered(eventName: String)

}
