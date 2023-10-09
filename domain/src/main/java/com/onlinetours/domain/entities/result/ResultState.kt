package com.onlinetours.domain.entities.result

sealed class ResultState {
    object Success : ResultState()
    data class Error(val message: String) : ResultState()
}