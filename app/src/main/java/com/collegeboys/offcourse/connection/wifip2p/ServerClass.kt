//package com.collegeboys.offcourse.connection.wifip2p
//
//import android.util.Log
//import java.io.IOException
//import java.net.InetAddress
//import java.net.ServerSocket
//import java.net.Socket
//import java.util.concurrent.ConcurrentHashMap
//
//class ServerClass : Thread() {
//    var socket: Socket? = null
//    var serverSocket: ServerSocket? = null
//
//    init {
//        netAddrSendReceiveHashMap = ConcurrentHashMap<InetAddress, SendReceive>()
//    }
//
//    override fun run() {
//        try {
//            serverSocket = ServerSocket(2323)
//            serverSocket!!.reuseAddress = true
//
//            while (true) {
//                Log.d("ServerClass", "run() listening to connections")
//                socket = serverSocket!!.accept()
//                socket!!.setKeepAlive(true)
//                Log.d(
//                    "ServerClass",
//                    "run() accepted connection from " + socket!!.getInetAddress().hostName
//                )
//                var sendReceive = SendReceive(socket)
//                netAddrSendReceiveHashMap?.put(socket!!.getInetAddress(), sendReceive!!)
//                Log.d("SendReceive Size", netAddrSendReceiveHashMap?.size.toString())
//                Log.d("ServerClass", "run() added client to sendReceiveHashMap")
//                sendReceive!!.start()
//            }
//        } catch (se: IOException) {
//            se.printStackTrace()
//            try {
//                netAddrSendReceiveHashMap?.remove(socket!!.inetAddress)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}
