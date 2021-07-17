package com.yazantarifi.android.issuer.data

enum class IssuesScreenMode(val key: String) {
    DIRECT_REPORT("DIRECT_REPORT"), // Show Screen With Text Field, Images
    LIST_SELECTION("LIST_SELECTION"), // Show PreSelected List Of Options Then Show Text Field In Screen
    FULL("FULL"), // Show List Options Screen Then Show Images Attachment Screen
}
