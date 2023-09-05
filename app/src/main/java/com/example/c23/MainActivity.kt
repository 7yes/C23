package com.example.c23

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.c23.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    companion object {
        const val MY_CHANNEL_ID = "myId"
        const val MY_CHANNEL = "myChannel"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        createChannel()
        scheduleNotification()
        setContentView(binding.root)
    }

    private fun scheduleNotification(){
        val intent = Intent(applicationContext,AlarmNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,1,intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis + 10000,pendingIntent)
        // Calerdar.timeinmillis
    }
    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                MY_CHANNEL,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Descripcion"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}