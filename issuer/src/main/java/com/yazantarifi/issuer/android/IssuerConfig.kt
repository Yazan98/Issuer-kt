package com.yazantarifi.issuer.android

import android.content.Intent
import android.os.Bundle
import com.yazantarifi.issuer.android.data.IssuerEvents
import com.yazantarifi.issuer.android.impl.IssuerListener

object IssuerConfig {

    private var listener: IssuerListener? = null

    fun addListener(listener: IssuerListener?) {
        this.listener = listener
    }

    fun getGlobalListener(): IssuerListener? {
        return listener
    }

    fun sendEventName(name: IssuerEvents, intent: Intent?) {
        val isEventsEnabled = intent?.extras?.getBoolean(IssuerConsts.IS_EVENTS_CLICK_ENABLED) ?: false
        if (isEventsEnabled) {
            getGlobalListener()?.onEventClickTriggered(name.key)
        }
    }

    fun sendEventName(name: IssuerEvents, bundle: Bundle?) {
        val isEventsEnabled = bundle?.getBoolean(IssuerConsts.IS_EVENTS_CLICK_ENABLED) ?: false
        if (isEventsEnabled) {
            getGlobalListener()?.onEventClickTriggered(name.key)
        }
    }

}
