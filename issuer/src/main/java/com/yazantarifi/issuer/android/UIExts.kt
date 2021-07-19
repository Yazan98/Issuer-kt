package com.yazantarifi.issuer.android

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import com.google.android.material.textfield.TextInputEditText
import com.yazantarifi.android.android.R
import com.yazantarifi.issuer.android.listeners.EmailSelectionListener
import java.lang.Exception


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