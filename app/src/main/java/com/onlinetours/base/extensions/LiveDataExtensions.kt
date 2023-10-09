package com.onlinetours.base.extensions

import androidx.lifecycle.MutableLiveData

// Set default value for any type of MutableLiveData
fun <T : Any> MutableLiveData<T>.default(initialValue: T) = apply { postValue(initialValue) }

// Set new value for any type of MutableLiveData
fun <T : Any> MutableLiveData<T>.set(newValue: T) = apply { setValue(newValue) }

fun <T : Any> MutableLiveData<T>.post(newValue: T) = apply { postValue(newValue) }