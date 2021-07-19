package com.yazantarifi.issuer.android

import com.yazantarifi.issuer.android.impl.IssuerListener


object IssuerConfig {

    private var listener: IssuerListener? = null

    fun addListener(listener: IssuerListener?) {
        this.listener = listener
    }

    fun getGlobalListener(): IssuerListener? {
        return listener
    }

}
