//package com.collegeboys.offcourse.connection.wifip2p
//
//import android.content.Context
//import android.net.wifi.p2p.WifiP2pConfig
//import android.net.wifi.p2p.WifiP2pManager
//import android.util.Log
//import android.widget.Toast
//import com.example.myapp.MainActivity
//import com.example.myapp.MainActivity.Companion.GOPWD
//import com.example.myapp.MainActivity.Companion.NETWORK_USERID
//import com.example.myapp.MainActivity.Companion.checkedForGroups
//import com.example.myapp.MainActivity.Companion.deviceArray
//import com.example.myapp.MainActivity.Companion.deviceNameArray
//import com.example.myapp.MainActivity.Companion.nameOfGO
//import com.example.myapp.MainActivity.Companion.NETWORK_USERNAME
//import com.example.myapp.MainActivity.Companion.peersScannedAtleastOnce
//import com.example.myapp.MainActivity.Companion.ssidList
//import com.example.myapp.MainActivity.Companion.wifiScannedAtleastOnce
//
//class CreateGroupOrConnect(
//    var wifiManager: WifiP2pManager?,
//    var wifiChannel: WifiP2pManager.Channel?,
//    var applicationContext: Context
//) : Thread() {
//    private var scanCount = 0
//    private var maxScans = 20
//    override fun run() {
//        while (NETWORK_USERNAME.isNullOrEmpty() || NETWORK_USERNAME.equals(NETWORK_USERID)) {
//            Log.d("CreateGroupOrConnect", "Username is not yet set")
//            sleep(1500)
//        }
//        while (!wifiScannedAtleastOnce || !peersScannedAtleastOnce) {
//            if (scanCount++ > maxScans) {
//                Log.d(
//                    "CreateGroupOrConnect",
//                    "20 scans exceeded, terminating scan and creating group"
//                )
//                break //no wifi direct devices available nearby
//            }
//            Log.d("CreateGroupOrConnect", "Waiting for wifi scan or peerlist scan")
//            Log.d(
//                "CreateGroupOrConnect",
//                "wifiScan = $wifiScannedAtleastOnce, peerScan = $peersScannedAtleastOnce"
//            )
//            sleep(2000)
//        }
//        checkedForGroups = true
//
//        if (scanCount > maxScans || ssidList.size == 0) {
//            Log.d("CreateGroupOrConnect", "I'll be GO because no other DIRECT groups available")
//            if (!MainActivity.groupCreated) {
//                Log.d("Clicked", "group not created, trying to create a group")
//
//                wifiManager!!.createGroup(wifiChannel, object : WifiP2pManager.ActionListener {
//                    override fun onSuccess() {
//                        Toast.makeText(
//                            applicationContext,
//                            "group successfully created ",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        Log.d("createGroup", "Successfully created a group")
//                        MainActivity.groupCreated = true
//
//                    }
//
//                    override fun onFailure(reason: Int) {
//                        Toast.makeText(
//                            applicationContext,
//                            "group not created!!",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        Log.d("createGroup", "group creation failure")
//                    }
//                })
//            }
//        } else {
//            Log.d("CreateGroupOrConnect", "I'll connect to GO because it exists near me")
//            for (deviceName in deviceNameArray) {
//
//                // WiFi Direct - motog4 <- GO
//                // WiFi scan - ssid name - DIRECT-xx-motog4
//
//                if (ssidList.contains(deviceName)) {
//                    nameOfGO = deviceName
//                    Log.d("CreateGroupOrConnect", "I'll connect to $deviceName")
//                    val device = deviceArray[deviceNameArray.indexOf(deviceName)]
//                    val config = WifiP2pConfig()
//                    config.deviceAddress = device!!.deviceAddress
//                    var success = false
//                    wifiManager!!.connect(
//                        wifiChannel,
//                        config,
//                        object : WifiP2pManager.ActionListener {
//                            override fun onSuccess() {
//                                Log.d("CreateGroupOrConnect", "Connected Successfully")
//                                Toast.makeText(
//                                    applicationContext,
//                                    "connected to " + device.deviceName,
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                GOPWD = "WD Connected to ${device.deviceName}"
//                                success = true
//                            }
//
//                            override fun onFailure(reason: Int) {
//                                Log.d("CreateGroupOrConnect", "Connection failure")
//                                Toast.makeText(
//                                    applicationContext,
//                                    "Not connected",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        })
//                    if (success) {
//                        break
//                    }
//                }
//            }
//        }
//    }
//}