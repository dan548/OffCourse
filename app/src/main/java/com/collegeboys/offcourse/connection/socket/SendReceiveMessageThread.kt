package com.collegeboys.offcourse.connection.socket

import android.os.Handler
import android.os.Looper
import java.io.IOException
import java.net.Socket

class SendReceiveMessageThread(private val socket: Socket?) : Thread() {
    private val inputStream = socket?.getInputStream()
    private val outputStream = socket?.getOutputStream()

    private val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        val readBuffer = it.obj as ByteArray
        val tempMessage = String(readBuffer, 0, it.arg1)
        // save message
        return@Callback true
    })

    override fun run() {
        val byteBuffer = ByteArray(1024)
        var bytes = 0

        while (socket != null) {
            try {
                bytes = inputStream?.read(byteBuffer)!!
                if (bytes > 0) {
                    handler.obtainMessage(1, bytes, -1, byteBuffer).sendToTarget()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}