package com.collegeboys.offcourse.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.collegeboys.offcourse.MainActivity.Companion.sendReceive
import com.collegeboys.offcourse.R

class ChatFragment : Fragment() {
    private lateinit var messageText: EditText
    private lateinit var sendButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        messageText = view.findViewById(R.id.written_message)
        sendButton = view.findViewById(R.id.send_message_button)
        sendButton.setOnClickListener { aView ->
            sendMessage(aView)
        }
        return view
    }

    fun sendMessage(view: View) {
        val text = messageText.text.toString()
        if (text.isNotEmpty()) {
            sendReceive.sendMessage(text)
            Toast.makeText(context, "sent", Toast.LENGTH_SHORT).show()
            messageText.text.clear()
        }
    }
}