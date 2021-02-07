package com.collegeboys.offcourse.connection.socket

import android.os.Looper
import com.collegeboys.offcourse.MainActivity.Companion.sendReceive
import java.net.ServerSocket
import java.net.Socket

class MessageReceiverThread(private val port: Int) : Thread() {
    private lateinit var socket: Socket
    private lateinit var receiverSocket: ServerSocket

    override fun run() {
        receiverSocket = ServerSocket(port)
        Looper.prepare()
        socket = receiverSocket.accept()

        sendReceive = SendReceiveMessageThread(socket)
        sendReceive.start()
    }
}