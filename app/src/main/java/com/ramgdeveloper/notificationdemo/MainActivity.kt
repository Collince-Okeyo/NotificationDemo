package com.ramgdeveloper.notificationdemo
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btn: Button
    private val channelID = "com.ramgdeveloper.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        creteNotificationChannel(channelID, "Notification Demo", "Simple notification demo")
        btn = findViewById(R.id.button)


        btn.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification(){

        val notificationId = 1
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Notification Demo")
            .setContentText("My simple notification demo")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun creteNotificationChannel(id: String, name: String, channelDescription: String){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance)
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager!!.createNotificationChannel(channel)
        }

    }


}