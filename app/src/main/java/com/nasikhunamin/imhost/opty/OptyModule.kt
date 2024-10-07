package com.nasikhunamin.imhost.opty

import android.app.Application
import android.content.Context
import android.content.Intent
import com.nasikhunamin.imhost.flutter.FlutterModuleHandler
import io.flutter.FlutterInjector
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class OptyModule private constructor() : FlutterModuleHandler {

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
            OptyActivity::class.java,
            getEngine()
        ).build(context).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
    }

    override fun getChannelName() = CHANNEL_NAME
    override fun getEntryPoint() = ENTRY_POINT
    override fun getEngine() = ENGINE_ID

    companion object {
        private const val CHANNEL_NAME = "OptyMethodChannel"
        private const val ENTRY_POINT = "optyMain"
        private const val ENGINE_ID = "optyMain"

        private lateinit var optyModule: OptyModule

        fun getInstance(): OptyModule {
            if (!::optyModule.isInitialized) {
                optyModule = OptyModule()
            }

            return optyModule
        }
    }
}