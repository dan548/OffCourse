package com.collegeboys.offcourse.utils

import java.net.NetworkInterface
import java.net.SocketException

object NetworkUtils {
    fun getIpAddress(): String {
        var ipAddress = ""
        try {
            val netInterfacesEnum = NetworkInterface.getNetworkInterfaces()
            while (netInterfacesEnum.hasMoreElements()) {
                val netInterface = netInterfacesEnum.nextElement()

                val netAddressesEnum = netInterface.inetAddresses
                while (netAddressesEnum.hasMoreElements()) {
                    val netAddress = netAddressesEnum.nextElement()
                    if (netAddress.isSiteLocalAddress) {
                        ipAddress = netAddress.hostAddress
                    }
                }
            }
        } catch (e: SocketException) {
            e.printStackTrace()
        }
        return ipAddress
    }
}