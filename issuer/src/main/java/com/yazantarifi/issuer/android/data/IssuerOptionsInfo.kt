package com.yazantarifi.issuer.android.data

import java.io.Serializable

data class IssuerOptionsInfo(
    val isCategoryNameEnabled: Boolean,
    val items: ArrayList<IssuerOption>
): Serializable
