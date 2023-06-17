package com.example.myapplication.ui.utils

object TextUtils {

    fun computeTokenUrlFrom(key: String): String {
        return "https://coinmarketcap.com/currencies/${key}/"
    }

}
