package com.txznet.common.model

import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.logV
import java.io.Closeable

/**
 * Created by Rick on 2023-01-30  16:57.
 * Description:访问网络的工具类封装在Model层，但是车载系统应用的 HMI 层通常没有访问网络的功能
 */
abstract class BaseRepository : Closeable {

    protected val TAG = CLASS_TAG

    override fun close() {
        logV(TAG, "[close]")
    }
}