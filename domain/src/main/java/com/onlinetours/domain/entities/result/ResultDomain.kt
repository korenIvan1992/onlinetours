package com.onlinetours.domain.entities.result


sealed class ResultDomain<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResultDomain<T>()
    data class Error(val message: String) : ResultDomain<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$message]"
        }
    }
}