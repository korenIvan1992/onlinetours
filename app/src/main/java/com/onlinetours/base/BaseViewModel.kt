package com.onlinetours.base

import com.onlinetours.base.extensions.default

abstract class BaseViewModel<S : Any>(defaultState: S) : IBaseViewModel() {

    // Init state and set default value
    val state = ActiveMutableLiveData<S>().default(initialValue = defaultState)

}