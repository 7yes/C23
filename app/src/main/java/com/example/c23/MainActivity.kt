package com.example.c23

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.c23.databinding.ActivityMainBinding

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
        simpleNotification()
        setContentView(binding.root)
    }

    fun simpleNotification() {
        var builder = NotificationCompat.Builder(this, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My Title")
            .setContentText("This is an Example")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("bla bla bla y mas bla bal bla")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

    fun createChannel() {
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