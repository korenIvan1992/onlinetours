package com.onlinetours

import android.app.Application
import com.onlinetours.base.di.component.DaggerMainComponent
import com.onlinetours.base.di.component.MainComponent

class MyApp : Application() {

    lateinit var mainComponent: MainComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        mainComponent = DaggerMainComponent.builder()
            .application(this)
            .context(this.applicationContext)
            .build()

    }
}


