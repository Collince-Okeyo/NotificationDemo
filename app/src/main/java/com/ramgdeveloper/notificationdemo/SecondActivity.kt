package com.ramgdeveloper.notificationdemo

import android.app.RemoteInput
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

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
        }

    }
}