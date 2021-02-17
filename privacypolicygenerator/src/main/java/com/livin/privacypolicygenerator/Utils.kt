package com.livin.privacypolicygenerator

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

object Utils {

    fun loadAssetTextAsString(context: Context, file: String): String {
        return context.assets.open(file).bufferedReader().use {
            it.readText()
        }
    }

    fun shareFile(file: File, context: Context, text: String) {
        try {
            if (file.exists()) {
                val uri = FileProvider.getUriForFile(
                    context,
                    context.applicationInfo.packageName + ".provider",
                    file
                )
                val intent = Intent(Intent.ACTION_SEND)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.type = "*/*"
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                intent.putExtra(Intent.EXTRA_TEXT, text)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
                context.startActivity(intent)
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}