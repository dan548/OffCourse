package com.collegeboys.offcourse.connection.socket

import java.net.Socket

class MessageSenderThread(
    private val hostAddress: String,
    private val port: Int
) : Thread() {
    private lateinit var socket: Socket
    private lateinit var sendReceiveMessageThread: SendReceiveMessageThread

    override fun run() {
        socket = Socket(hostAddress, port)
        sendReceiveMessageThread = SendReceiveMessageThread(socket)
        sendReceiveMessageThread.start()
    }
}