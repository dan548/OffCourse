//package com.collegeboys.offcourse.connection.wifip2p
//
//import android.util.Log
//import com.collegeboys.offcourse.MainActivity
//
//import java.io.IOException
//import java.net.InetAddress
//import java.net.InetSocketAddress
//import java.net.Socket
//
//class ClientClass(private val hostInetAddress: InetAddress) : Thread() {
//    var socket: Socket? = null
//    private var hostAddress: String = hostInetAddress.hostAddress
//
//    override fun run() {
//        try {
//            socket = Socket()
//            socket!!.keepAlive = true
//
//            socket!!.connect(InetSocketAddress(hostAddress, 2323), 500)
//            var sendReceive = SendReceive(socket)
//            Log.d("ClientClass", "run() sendReceive Object Created")
//
//            MainActivity.netAddrSendReceiveHashMap?.put(socket!!.getInetAddress(), sendReceive!!)
//
//            sendReceive!!.start()
//            connectedToDeviceAlert()
//            broadcastMessage("$NETWORK_USERID $NETWORK_USERNAME", WifiP2pConstants.DATA_TYPE_UNIQID_USERNAME)
//        } catch (e: IOException) {
//            socket = Socket()
//            e.printStackTrace()
//        }
//    }
//}
