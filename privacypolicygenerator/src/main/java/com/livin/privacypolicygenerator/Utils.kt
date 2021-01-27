package com.livin.privacypolicygenerator

import android.content.Context

object Utils {

    fun loadAssetTextAsString(context: Context, file: String): String {
        return context.assets.open(file).bufferedReader().use {
            it.readText()
        }
    }

}