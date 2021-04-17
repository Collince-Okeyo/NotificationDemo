package com.ramgdeveloper.notificationdemo
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btn: Button
    private val channelID = "com.ramgdeveloper.notificationdemo.channel1"
    private val channelName = "MyChannel"
    private val notificationId = 1
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        creteNotificationChannel()
        btn = findViewById(R.id.button)


        btn.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification(){

        // Building Notification
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Notification Demo")
            .setContentText("My simple notification demo")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId,notification)
    }

    private fun creteNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager!!.createNotificationChannel(channel)

        }

    }


}