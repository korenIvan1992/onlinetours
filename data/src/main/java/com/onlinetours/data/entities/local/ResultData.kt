package com.ringtone2appwallpapers.data.entities.local

sealed class ResultData {
    object Success : ResultData()
    data class Error(val message: String) : ResultData()
}