package com.yazantarifi.example.android

import android.app.Application
import com.yazantarifi.issuer.android.IssuerConfig
import com.yazantarifi.issuer.android.impl.IssuerListener

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        IssuerConfig.addListener(object: IssuerListener {
            override fun onErrorTriggered(error: Throwable) {
                // Log Exception
            }

            override fun onEventClickTriggered(eventName: String) {
                // Do Whatever on Events Like Show Message, Send Event Name, etc
            }
        })
    }

}
