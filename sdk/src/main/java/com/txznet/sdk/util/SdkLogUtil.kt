package com.txznet.sdk.util

import android.util.Log

/**
 * Created by Rick on 2023-02-03  14:40.
 */


/**
 * Print verbose log info.
 *
 * @param tag  title
 * @param info description
 */
fun logV(tag: String?, info: String) = SdkLogUtils.v(tag, info)

/**
 * TAG
 */
val Any.CLASS_TAG get() = this::class.java.simpleName + ":" + Integer.toHexString(hashCode())

object SdkLogUtils {
    const val TAG_SDK = "[SDK]-LogUtil："
    private var VERBOSE = true
    fun init(verbose: Boolean) {
        VERBOSE = verbose
    }

    fun v(tag: String?, info: String) {
        if (VERBOSE) {
            Log.v(tag, "[thread:" + Thread.currentThread().name + "] - " + info)
        }
    }
}