package com.example.c23

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class AlarmNotification:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        simpleNotification(context)
    }

    fun simpleNotification(context: Context) {
        val intent = Intent(context,MainActivity::class.java).apply {
            // esta flags evita que se abran mas applicaiones y evitar abrirla 3 o 4 veces etc
            flags= Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(context, MainActivity.MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My Title")
            .setContentText("This is an Example")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("bla bla bla y mas bla bal bla")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,notification)
    }
}