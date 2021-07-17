package com.yazantarifi.android.issuer.data

import android.os.Parcelable
import java.io.Serializable

data class IssuerOptionsInfo(
    val isCategoryNameEnabled: Boolean,
    val items: ArrayList<IssuerOption>
): Serializable
