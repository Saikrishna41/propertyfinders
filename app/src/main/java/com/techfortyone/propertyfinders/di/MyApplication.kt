package com.techfortyone.propertyfinders.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}