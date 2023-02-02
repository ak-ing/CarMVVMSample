package com.txznet.common

import android.app.Application
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.LogUtil

/**
 * Created by Rick on 2023-01-30  21:12.
 * Description: 全局ApplicationContext
 */
object AppGlobal {
    private val TAG = LogUtil.TAG_COMMON + CLASS_TAG
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