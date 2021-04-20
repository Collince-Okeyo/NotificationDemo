package com.ramgdeveloper.notificationdemo
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {

    private lateinit var btn: Button
    private val channelID = "com.ramgdeveloper.notificationdemo.channel1"
    private val channelName = "MyChannel"
    private val notificationId = 1
    private var notificationManager: NotificationManager? = null
    private val keyReply = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        btn = findViewById(R.id.button)


        btn.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification(){

        // Tapping the notification to opening next activity
        val secondActivityIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent:PendingIntent = PendingIntent.getActivity(this,0, secondActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Action Buttons in Notification for Details Activity
        val intentDetails = Intent(this, DetailActivity::class.java)
        val pendingIntentDetails = PendingIntent.getActivity(this, 0, intentDetails, PendingIntent.FLAG_UPDATE_CURRENT)
        val actionDetails: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Details", pendingIntentDetails).build()

        // Action Buttons in Notification for Settings Activity
        val intentSettings = Intent(this, SettingsActivity::class.java)
        val pendingIntentSettings = PendingIntent.getActivity(this, 0, intentSettings, PendingIntent.FLAG_UPDATE_CURRENT)
        val actionSettings: NotificationCompat.Action = NotificationCompat.Action.Builder(0, "Settings", pendingIntentSettings).build()

        //Reply Action on Notification
        val remoteInput: RemoteInput = RemoteInput.Builder(keyReply).run {
            setLabel("Enter Your reply...")
            build()
        }

        val replyAction: NotificationCompat.Action = NotificationCompat.Action.Builder(
                0,
                "Reply",
                pendingIntent
        ).addRemoteInput(remoteInput).build()


        // Building Notification
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Notification Demo")
            .setContentText("My simple notification demo")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
                //.setContentIntent(pendingIntent)
                // setting actions on detail and settings
                .addAction(actionDetails)
                .addAction(actionSettings)
                .addAction(replyAction)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId,notification)
    }

    private fun createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager!!.createNotificationChannel(channel)

        }

    }


}