package com.collegeboys.offcourse.fragments

import com.collegeboys.offcourse.R
import com.collegeboys.offcourse.connection.socket.ReceiveMessageServer
import com.collegeboys.offcourse.connection.socket.SendMessageThread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class ChatFragment : Fragment() {
    private lateinit var messageText: EditText
    private lateinit var sendButton: ImageButton

    private lateinit var messageServer: ReceiveMessageServer
    private lateinit var userIpAddress: String
    private lateinit var userPort: String

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

        setFragmentResultListener(getString(R.string.my_message_params)) { _, bundle ->
            messageServer = ReceiveMessageServer(bundle.getString("port")!!.toInt())
        }

        setFragmentResultListener(getString(R.string.companion_message_params)) { _, bundle ->
            userIpAddress = bundle.getString("ip_address")!!
            userPort = bundle.getString("port")!!
        }
        return view
    }

    fun sendMessage(view: View) {
        val text = messageText.text.toString()
        if (text.isNotEmpty()) {
            val sender = SendMessageThread(userIpAddress, userPort.toInt(), text)
            sender.start()
        }
        messageText.text.clear()
    }
}