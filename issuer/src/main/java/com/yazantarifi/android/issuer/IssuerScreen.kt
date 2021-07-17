package com.yazantarifi.android.issuer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.yazantarifi.android.issuer.impl.IssuerScreenImplementation

class IssuerScreen: AppCompatActivity(), IssuerScreenImplementation {

    companion object {

        @JvmStatic
        inline fun startScreen(context: FragmentActivity?, intent: (IssuerIntent) -> IssuerIntent) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_issuer)
    }

}
