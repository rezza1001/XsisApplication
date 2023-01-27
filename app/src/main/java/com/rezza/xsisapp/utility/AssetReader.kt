package com.rezza.xsisapp.utility

import android.content.Context
import java.io.IOException

class AssetReader {

    companion object {
        fun getStringFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }

}