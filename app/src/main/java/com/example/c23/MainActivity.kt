package com.example.c23

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.c23.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var alarmMgr: AlarmManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this
        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        binding.btnCreate.setOnClickListener { alarm("create") }
        binding.btnUpdate.setOnClickListener { alarm("update") }
        binding.btnCancel.setOnClickListener { alarm("cancel") }
    }
    private fun alarm(s: String) {
        val seconds = binding.etTimer.text.toString().toInt() * 1000
        val intent = Intent(context, Receiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
        when (s) {
            "create" -> {
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
                Log.d("TAG", "onCreate: ${Date().toString()} ")
            }
            "update" -> {
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
                Log.d("TAG", "update: ${Date().toString()} ")
            }
            "cancel" ->{
                Log.d("TAG", "onUpdate: ${Date().toString()} ")
                alarmMgr.cancel(pendingIntent)
            }
        }
    }
}