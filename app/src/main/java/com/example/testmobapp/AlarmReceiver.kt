package com.example.testmobapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: return
        println("Triggered message: $message")
    }
}