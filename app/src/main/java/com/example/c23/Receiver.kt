package com.example.c23

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Date

class Receiver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("TAG", "onReceive: "+ Date().toString())
    }
}