package com.yazantarifi.issuer.android.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IssuerOption(
    val title: String? = "",
    val options: ArrayList<String>?
): Parcelable
