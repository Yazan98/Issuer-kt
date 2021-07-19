package com.yazantarifi.issuer.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.impl.IssuerScreenImplementation
import java.lang.Exception

class IssuerScreen: AppCompatActivity(), IssuerScreenImplementation {

    companion object {
        @JvmStatic
        inline fun startScreen(context: FragmentActivity?, intent: () -> Unit) {
            try {
                intent()
                context?.startActivityForResult(IssuerIntent.build(context), IssuerConsts.REQUEST_CODE)
            } catch (ex: Exception) {
                IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_issuer)
        initScreenTitle(intent?.extras)
    }

    override fun initScreenTitle(extras: Bundle?) {
//        screenToolbar
    }

}
