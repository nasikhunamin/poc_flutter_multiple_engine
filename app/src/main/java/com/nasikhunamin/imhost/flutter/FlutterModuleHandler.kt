package com.nasikhunamin.imhost.flutter

import android.app.Application
import android.content.Context
import android.content.Intent

interface FlutterModuleHandler {
    fun preWarmEngine(application: Application)
    fun buildWithCachedEngine(context: Context): Intent
    fun getChannelName(): String
    fun getEntryPoint(): String
    fun getEngine(): String
}