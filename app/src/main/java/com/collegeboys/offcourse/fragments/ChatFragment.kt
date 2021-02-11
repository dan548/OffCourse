package com.collegeboys.offcourse.fragments

import android.content.Context
import com.collegeboys.offcourse.R
import com.collegeboys.offcourse.adapter.MessageAdapter
import com.collegeboys.offcourse.database.entity.Message
import com.collegeboys.offcourse.connection.socket.ReceiveMessageServer
import com.collegeboys.offcourse.connection.socket.SendMessageThread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import java.util.*

class ChatFragment : Fragment() {
    private lateinit var messageListElement: ListView
    private lateinit var messageText: EditText
    private lateinit var sendButton: ImageButton

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageServer: ReceiveMessageServer

    private lateinit var ownerId: String
    private lateinit var userIpAddress: String
    private lateinit var userPort: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
        ownerId = preferences?.getString(
            getString(R.string.shared_pref_user_id_key), ""
        ) ?: ""
        messageAdapter = MessageAdapter(
            requireContext(),
            ownerId,
            UUID.randomUUID().toString()
        )

        messageListElement = view.findViewById(R.id.messages_view)
        messageListElement.adapter = messageAdapter

        messageText = view.findViewById(R.id.written_message)
        sendButton = view.findViewById(R.id.send_message_button)
        sendButton.setOnClickListener {
            sendMessage()
        }

        setFragmentResultListener(getString(R.string.my_message_params)) { _, bundle ->
            messageServer = ReceiveMessageServer(
                bundle.getString("port")!!.toInt(),
                messageAdapter
            )
        }

        setFragmentResultListener(getString(R.string.companion_message_params)) { _, bundle ->
            userIpAddress = bundle.getString("ip_address")!!
            userPort = bundle.getString("port")!!
        }
        return view
    }

    fun sendMessage() {
        val text = messageText.text.toString()
        if (text.isNotEmpty()) {
            val sender = SendMessageThread(userIpAddress, userPort.toInt(), text)
            sender.start()
            messageAdapter.add(Message(
                senderId = ownerId,
                recipientId = messageAdapter.otherUserId,
                text = text
            ))
        }
        messageText.text.clear()
    }
}