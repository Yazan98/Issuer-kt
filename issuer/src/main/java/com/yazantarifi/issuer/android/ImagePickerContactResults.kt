package com.yazantarifi.issuer.android

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ImagePickerContactResults: ActivityResultContract<Unit, Uri>() {

    private var photoUri: Uri? = null
    companion object {
        fun getRealPathFromURI(context: Context, contentURI: Uri): String? {
            val result: String?
            val cursor: Cursor? = context.contentResolver.query(contentURI, null, null, null, null)
            if (cursor == null) {
                result = contentURI.path
            } else {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                result = cursor.getString(idx)
                cursor.close()
            }
            return result
        }
    }

    override fun createIntent(context: Context, input: Unit?): Intent {
        return openImageIntent(context)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        if (resultCode != Activity.RESULT_OK) return null
        return intent?.data ?: photoUri
    }

    private fun openImageIntent(context: Context): Intent {
        photoUri = createPhotoTakenUri(context)
        val gallIntent = Intent(Intent.ACTION_GET_CONTENT)
        gallIntent.type = "image/*"

        // look for available intents
        val info = ArrayList<ResolveInfo>()
        val yourIntentsList = ArrayList<Intent>()
        val packageManager = context.packageManager

        packageManager.queryIntentActivities(gallIntent, 0).forEach {
            val finalIntent = Intent(gallIntent)
            finalIntent.component = ComponentName(it.activityInfo.packageName, it.activityInfo.name)
            yourIntentsList.add(finalIntent)
            info.add(it)
        }

        val chooser = Intent.createChooser(gallIntent, "Select source")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, yourIntentsList.toTypedArray())

        return chooser

    }

    private fun createFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: throw IllegalStateException("Dir not found")
        return File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
    }

    private fun createPhotoTakenUri(context: Context): Uri {
        val file = createFile(context)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(context, "Issuer" + ".provider", file)
        } else {
            Uri.fromFile(file)
        }
    }
}