package com.onlinetours.ui.utills

import com.onlinetours.domain.entities.result.Tour


sealed class SearchState {
    object Suspense : SearchState()
    object Loading : SearchState()
    data class Error(val message: String) : SearchState()
    data class ErrorInt(val message: Int) : SearchState()
    data class Success(val list: List<Any>) : SearchState()
}