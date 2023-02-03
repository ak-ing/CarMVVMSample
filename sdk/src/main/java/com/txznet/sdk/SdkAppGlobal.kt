package com.txznet.sdk

import android.app.Application
import com.txznet.sdk.util.CLASS_TAG
import com.txznet.sdk.util.SdkLogUtils

/**
 * Created by Rick on 2023-01-30  21:12.
 * Description: 全局ApplicationContext
 */
object SdkAppGlobal {
    private val TAG = SdkLogUtils.TAG_SDK + CLASS_TAG
    private lateinit var app: Application

    fun init(application: Application) {
        app = application
    }

    val context: Application
        get() {
            if (!this::app.isInitialized) {
                error("$TAG AppGlobal context is not initialize.")
            }
            return app
        }
}