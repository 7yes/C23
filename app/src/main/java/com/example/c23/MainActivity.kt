package com.example.c23

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.c23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        notificationFu()
    }
    private fun notificationFu() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager //sirve se agrega postpermission en manifest
//  val notificationManager= NotificationManagerCompat.from(this) // sirve hay q add permission check
        val channelComm =
            NotificationChannel("MiId", "NOmbre del canal", NotificationManager.IMPORTANCE_HIGH)
        channelComm.description = "es el mensaje"
        notificationManager.createNotificationChannel(channelComm)

        val notification = NotificationCompat.Builder(this, "MiId")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(androidx.core.R.drawable.ic_call_decline)
            .setContentTitle("Es el Titulo")
            .setContentText("Es el mensaje de bla bla bla bla bla bla bla")

        notificationManager.notify(1, notification.build())
    }
}