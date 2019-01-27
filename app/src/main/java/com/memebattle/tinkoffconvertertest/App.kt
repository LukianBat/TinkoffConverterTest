package com.memebattle.tinkoffconvertertest

import android.app.Application
import com.memebattle.tinkoffconvertertest.core.di.AppComponent
import com.memebattle.tinkoffconvertertest.core.di.DaggerAppComponent
import com.memebattle.tinkoffconvertertest.core.di.module.RoomModule

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder().roomModule(RoomModule(applicationContext)).build()
    }
}