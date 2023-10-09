package com.onlinetours.base

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class ActiveMutableLiveData<T>  : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)
    private val values: Queue<T> = LinkedList()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Timber.w(this::class.java.name, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner) { t: T ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
                //call next value processing if have such
                if (values.isNotEmpty())
                    pollValue()
            }
        }
    }

    override fun postValue(value: T) {
        values.add(value)
        pollValue()
    }

    private fun pollValue() {
        value = values.poll()
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @Suppress("unused")
    @MainThread
    fun call() {
        value = null
    }
}