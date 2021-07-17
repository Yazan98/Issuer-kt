package com.yazantarifi.android.issuer

import com.yazantarifi.android.issuer.impl.IssuerListener

object IssuerConfig {

    private var listener: IssuerListener? = null

    fun addListener(listener: IssuerListener?) {
        this.listener = listener
    }

    internal fun getGlobalListener(): IssuerListener? {
        return listener
    }

}
