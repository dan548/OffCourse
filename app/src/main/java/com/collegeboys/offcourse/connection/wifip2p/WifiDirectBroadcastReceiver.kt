//package com.collegeboys.offcourse.connection.wifip2p
//
//import com.collegeboys.offcourse.MainActivity
//
//import android.annotation.SuppressLint
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.net.NetworkInfo
//import android.net.wifi.p2p.WifiP2pManager
//import android.widget.Toast
//
//class WifiDirectBroadcastReceiver(
//    private val wifiManger: WifiP2pManager?,
//    private val wifiChannel: WifiP2pManager.Channel,
//    private val activity: MainActivity
//) : BroadcastReceiver() {
//
//    @SuppressLint("MissingPermission")
//    override fun onReceive(context: Context, intent: Intent) {
//        when (intent.action) {
//            WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
//                val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
//                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
//                    Toast.makeText(context, "WIFI IS ON", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "WIFI IS OFF", Toast.LENGTH_SHORT).show()
//                }
//            }
//            WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
//                wifiManger?.requestPeers(wifiChannel, activity.peerListListener)
//            }
//            WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
//                if (wifiManger == null) {
//                    return
//                }
//                val networkInfo = intent.getParcelableExtra<NetworkInfo>(
//                    WifiP2pManager.EXTRA_NETWORK_INFO
//                )
//                if (networkInfo!!.isConnected) {
//                    wifiManger.requestConnectionInfo(wifiChannel, activity.connectionInfoListener)
//                }
//            }
//            WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
//
//            }
//        }
//    }
//}
