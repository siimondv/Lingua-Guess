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

    // Define the enum with different network statuses
    enum class NetworkStatus {
        Available, Unavailable, Losing, Lost
    }

    // Flow to emit network statuses
    val networkStatus: Flow<NetworkStatus> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available).isSuccess // Emit Available status
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Lost).isSuccess // Emit Lost status
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetworkStatus.Losing).isSuccess // Emit Losing status
            }

            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable).isSuccess // Emit Unavailable status
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        // Clean up the callback when the Flow is canceled
        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }
}