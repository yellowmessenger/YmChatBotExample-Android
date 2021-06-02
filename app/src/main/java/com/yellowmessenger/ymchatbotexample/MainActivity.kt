package com.yellowmessenger.ymchatbotexample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yellowmessenger.ymchat.YMChat
import com.yellowmessenger.ymchat.YMConfig
import com.yellowmessenger.ymchat.models.YMBotEventResponse
import java.util.*

class MainActivity : AppCompatActivity() {
    private var botId = "x1587041004122"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val theme = this.theme
        initChatBot()
    }

    private fun initChatBot() {

        //Get YMChat instance
        val ymChat: YMChat = YMChat.getInstance()
        ymChat.config = YMConfig(botId)
        //Payload attributes
        val payloadData = HashMap<String, Any>()
        //Setting Payload Data
        payloadData["some-key"] = "some-value"
        ymChat.config.payload = payloadData
        ymChat.config.enableHistory = true

        //setting event listener

        //setting event listener
        ymChat.onEventFromBot { botEvent: YMBotEventResponse ->
            when (botEvent.code) {
                "event-name" -> {
                }
            }
        }
        ymChat.onBotClose { Log.d("Example App", "Bot Was closed") }


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view: View? ->
            //Starting the bot activity
            try {
                ymChat.startChatbot(this)
            } catch (e: Exception) {
                //Catch and handle the exception
                e.printStackTrace()
            }
        }
    }
}