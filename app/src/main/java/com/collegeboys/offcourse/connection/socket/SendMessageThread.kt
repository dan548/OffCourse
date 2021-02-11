package com.collegeboys.offcourse.connection.socket

import android.util.Log
import java.io.IOException
import java.io.ObjectOutputStream
import java.net.Socket

class SendMessageThread(
        private val destinationAddress: String,
        private val destinationPort: Int,
        private val message: String
) : Thread() {
    private val TAG = "SendMessageThread"
    private lateinit var clientSocket: Socket

    override fun run() {
        try {
            Log.d(TAG, "Sending message to $destinationAddress:$destinationPort")
            clientSocket = Socket(destinationAddress, destinationPort)

            val outStream = ObjectOutputStream(clientSocket.getOutputStream())
            outStream.writeUTF(message)
            outStream.flush()
            Log.d(TAG, "Message sent")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}