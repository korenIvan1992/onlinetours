package com.onlinetours.base

import androidx.lifecycle.ViewModel

abstract class IBaseViewModel : ViewModel() {

    abstract fun backPress()
}