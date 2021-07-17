package com.yazantarifi.android.issuer.data

/**
 * Information Types Selected From Developer
 * Developers Can Decide Which is The Best Scenario To Collect Data For Their Applications
 */
enum class IssueInfoType constructor(val key: String) {
    FULL("FULL"), // Collect Full Information From Device
    SYSTEM("SYSTEM"), // Collect General Information From System
    TEXT("TEXT") // Open Text Screen To Let User Write Everything He want
}
