package com.nasikhunamin.imhost.im

import android.app.Application
import android.content.Context
import android.content.Intent
import com.nasikhunamin.imhost.flutter.FlutterModuleHandler
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class ImModule private constructor() : FlutterModuleHandler {

    private lateinit var flutterEngine: FlutterEngine

    override fun preWarmEngine(application: Application) {
        flutterEngine = FlutterEngine(application)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(), getEntryPoint()
            )
        )
        FlutterEngineCache
            .getInstance()
            .put(ENGINE_ID, flutterEngine)
    }

    override fun buildWithCachedEngine(context: Context): Intent {
        return FlutterActivity.CachedEngineIntentBuilder(
            ImActivity::class.java,
            getEngine()
        ).build(context).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
    }

    override fun getChannelName() = CHANNEL_NAME
    override fun getEntryPoint() = ENTRY_POINT
    override fun getEngine() = ENGINE_ID

    companion object {
        private const val CHANNEL_NAME = "ImMethodChannel"
        private const val ENTRY_POINT = "imMain"
        private const val ENGINE_ID = "imMain"

        private lateinit var imModule: ImModule

        fun getInstance(): ImModule {
            if (!::imModule.isInitialized) {
                imModule = ImModule()
            }

            return imModule
        }
    }
}