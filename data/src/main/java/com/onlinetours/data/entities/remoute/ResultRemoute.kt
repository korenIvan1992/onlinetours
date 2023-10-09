package com.ringtone2appwallpapers.data.entities.remoute


sealed class ResultRemoute<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResultRemoute<T>()
    data class Error(val message: String) : ResultRemoute<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$message]"
        }
    }
}