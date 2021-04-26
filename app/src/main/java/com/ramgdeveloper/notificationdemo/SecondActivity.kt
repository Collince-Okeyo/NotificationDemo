package com.ramgdeveloper.notificationdemo

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        text = findViewById(R.id.textView3)
        receiveInput()
    }

    private fun receiveInput(){
        val keyReply = "key_reply"
        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if (remoteInput != null) {
            val inputString = remoteInput.getCharSequence(keyReply).toString()
            text.text = inputString

            val channelID = "com.ramgdeveloper.notificationdemo.channel1"
             val notificationId = 1

            val repliedNotification = NotificationCompat.Builder(this, channelID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentText("Replied Successfully")
                    .build()

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)

        }

    }
}