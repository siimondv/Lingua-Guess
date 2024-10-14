package com.example.linguaguess.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkStatusTracker(private val context: Context) {


    enum class NetworkStatus {
        Available, Unavailable, Losing, Lost
    }


    val networkStatus: Flow<NetworkStatus> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available).isSuccess
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Lost).isSuccess
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetworkStatus.Losing).isSuccess
            }

            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable).isSuccess
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)


        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }
}