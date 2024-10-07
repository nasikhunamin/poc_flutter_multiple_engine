package com.nasikhunamin.imhost

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.nasikhunamin.imhost.im.ImModule
import com.nasikhunamin.imhost.opty.OptyModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        ImModule.getInstance().preWarmEngine(this)
        OptyModule.getInstance().preWarmEngine(this)
    }
}