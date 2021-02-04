package com.collegeboys.offcourse.connection.socket

import android.os.Looper
import java.net.ServerSocket
import java.net.Socket

class MessageReceiverThread(private val port: Int) : Thread() {
    private lateinit var socket: Socket
    private lateinit var receiverSocket: ServerSocket
    private lateinit var sendReceiveMessageThread: SendReceiveMessageThread

    override fun run() {
        receiverSocket = ServerSocket(port)
        Looper.prepare()
        socket = receiverSocket.accept()

        sendReceiveMessageThread = SendReceiveMessageThread(socket)
        sendReceiveMessageThread.start()
    }
}