package com.yazantarifi.issuer.android

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import com.google.android.material.textfield.TextInputEditText
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.listeners.EmailSelectionListener


fun showEmailDialog(context: Context, title: String, message: String, listener: EmailSelectionListener) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    val viewInflated: View = LayoutInflater.from(context).inflate(R.layout.text_input_password, null, false)
    builder.setView(viewInflated)
    builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
        try {
            val textInput = viewInflated.findViewById<TextInputEditText>(R.id.input)?.text?.toString()?.trim()
            listener.onEmailSelected(textInput)
            dialog.dismiss()
        } catch (ex: Exception) {
            listener.onEmailSelected(null)
            IssuerConfig.getGlobalListener()?.onErrorTriggered(ex)
        }
    }

    builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
        dialog.cancel()
        listener.onDialogDismissed()
    }
    builder.show()
}

fun getStringArgument(value: String): NavArgument {
    return NavArgument.Builder()
        .setDefaultValue(value)
        .setType(NavType.StringType)
        .build()
}

fun getBooleanArgument(value: Boolean): NavArgument {
    return NavArgument.Builder()
        .setDefaultValue(value)
        .setType(NavType.BoolType)
        .build()
}

fun getNetworkClass(context: Context): String? {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = cm.activeNetworkInfo
    if (info == null || !info.isConnected) return "-"
    if (info.type == ConnectivityManager.TYPE_WIFI) return "WIFI"
    if (info.type == ConnectivityManager.TYPE_MOBILE) {
        return when (info.subtype) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN, TelephonyManager.NETWORK_TYPE_GSM -> "2G"
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP, TelephonyManager.NETWORK_TYPE_TD_SCDMA -> "3G"
            TelephonyManager.NETWORK_TYPE_LTE, TelephonyManager.NETWORK_TYPE_IWLAN, 19 -> "4G"
            TelephonyManager.NETWORK_TYPE_NR -> "5G"
            else -> "?"
        }
    }
    return "?"
}