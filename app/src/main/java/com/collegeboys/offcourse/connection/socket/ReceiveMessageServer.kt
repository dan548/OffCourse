package com.collegeboys.offcourse.connection.socket

import android.util.Log
import java.io.IOException
import java.io.ObjectInputStream
import java.net.ServerSocket

class ReceiveMessageServer(port: Int) {
    private lateinit var serverSocket: ServerSocket
    private var stop = false

    init {
        val thread = ReceiveMessageThread(port)
        thread.start()
    }

    fun onDestroy() {
        try {
            serverSocket.close()
            stop = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    inner class ReceiveMessageThread(private val port: Int) : Thread() {
        private val TAG = "ReceiveMessageThread"

        override fun run() {
            try {
                serverSocket = ServerSocket(port)
                Log.d(TAG, "Started message server")
                while (!stop) {
                    val receivedUserSocket = serverSocket.accept()
                    try {
                        val inStream = ObjectInputStream(receivedUserSocket.getInputStream())
                        val stingMessage = inStream.readUTF()
                        Log.d(TAG, "Received message")
                        Log.d(TAG, "Message: $stingMessage")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}