package com.txznet.common.utils

import android.text.TextUtils
import android.util.Log

/**
 * Created by Rick on 2023-01-30  17:49.
 * Description: 日志工具类
 */

private const val LOG_V = 1
private const val LOG_D = 2
private const val LOG_I = 3
private const val LOG_W = 4
private const val LOG_E = 5

@JvmName("logV")
fun logV(tag: String? = null, content: String) {
    LogUtil.showLogWithLineNum(tag, LOG_V, content)
}

@JvmName("logD")
fun logD(tag: String? = null, content: String) {
    LogUtil.showLogWithLineNum(tag, LOG_D, content)
}

@JvmName("logI")
fun logI(tag: String? = null, content: String) {
    LogUtil.showLogWithLineNum(tag, LOG_I, content)
}

@JvmName("logW")
fun logW(tag: String? = null, content: String) {
    LogUtil.showLogWithLineNum(tag, LOG_W, content)
}

@JvmName("logE")
fun logE(tag: String? = null, content: String) {
    LogUtil.showLogWithLineNum(tag, LOG_E, content)
}


object LogUtil {
    const val TAG_COMMON = "[common_Log]"
    private var logEnable = true

    fun enableLog(enable: Boolean) {
        logEnable = enable
    }

    fun showLogWithLineNum(tag: String?, logLevel: Int, content: String) {
        if (!logEnable) {
            return
        }
        val contents = getAutoJumpLogInfos()
        if (!TextUtils.isEmpty(tag) && tag != contents[0]) {
            contents[0] = contents[0] + ":" + tag
        }
        if (TextUtils.isEmpty(content)) {
            when (logLevel) {
                LOG_V -> Log.v(contents[0] + "." + contents[1], contents[2] + content)
                LOG_D -> Log.d(contents[0] + "." + contents[1], contents[2] + content)
                LOG_I -> Log.i(contents[0] + "." + contents[1], contents[2] + content)
                LOG_W -> Log.w(contents[0] + "." + contents[1], contents[2] + content)
                LOG_E -> Log.e(contents[0] + "." + contents[1], contents[2] + content)
            }
            return
        }
        var index = 0
        var size = 1024 * 3
        while (index < content.length) {
            if (content.length < index + size) {
                size = content.length - index
            }
            val subContent = content.substring(index, index + size)
            index += size
            when (logLevel) {
                LOG_V -> Log.v(contents[0] + "." + contents[1], contents[2] + subContent)
                LOG_D -> Log.d(contents[0] + "." + contents[1], contents[2] + subContent)
                LOG_I -> Log.i(contents[0] + "." + contents[1], contents[2] + subContent)
                LOG_W -> Log.w(contents[0] + "." + contents[1], contents[2] + subContent)
                LOG_E -> Log.e(contents[0] + "." + contents[1], contents[2] + subContent)
            }
        }
    }

    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private fun getAutoJumpLogInfos(): Array<String> {
        val contents = arrayOf("", "", "")
        val stackTrace = Thread.currentThread().stackTrace
        return if (stackTrace.size < 6) {
            Log.e(TAG_COMMON, "Stack is too shallow!!!")
            contents
        } else {
            contents[0] = "txz_link_" + stackTrace[5].className.substring(
                stackTrace[5].className.lastIndexOf(".") + 1
            )
            contents[1] = stackTrace[5].methodName + "()"
            contents[2] = (" (" + stackTrace[5].fileName + ":"
                    + stackTrace[5].lineNumber + ") ")
            contents
        }
    }
}


