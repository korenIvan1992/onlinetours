package com.onlinetours.domain.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object NetworkUtils {
    suspend fun checkInternetStatus() : Boolean{
        return suspendCoroutine {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val sock = Socket()
                    sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                    sock.close()
                    it.resume(true)
                } catch (e: IOException) {
                    it.resume(false)
                }
            }
        }

    }
}