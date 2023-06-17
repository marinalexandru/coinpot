package com.example.myapplication.data.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject


class IntentDispatchServiceImpl @Inject constructor(
    private val context: Context
) : IntentDispatchService {

    override fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.parse(url)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // for demo purposes it is ignored
            e.printStackTrace()
        }
    }
}
