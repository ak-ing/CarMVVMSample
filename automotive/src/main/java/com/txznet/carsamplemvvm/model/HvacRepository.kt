package com.txznet.carsamplemvvm.model

import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.LogUtil.TAG_COMMON
import com.txznet.common.utils.logI
import com.txznet.common.utils.logV
import com.txznet.sdk.hvac.DefaultHvacCallback
import com.txznet.sdk.hvac.HvacManager

/**
 * Created by Rick on 2023-01-30  20:54.
 * Description:
 */
class HvacRepository : BaseRepository(), DefaultHvacCallback {
    private val TAG: String = TAG_COMMON + CLASS_TAG

    private val mHvacManager = HvacManager.instant
    private var mHvacVMCallback: IHvacCallback? = null

    override fun onTemperatureChanged(temperature: Double) {
        logV(TAG, "onTemperatureChanged：$temperature")
        mHvacVMCallback?.let {
            // 处理远程数据，将它转换为应用中需要的数据格式或内容
            val value: String = temperature.toString()
            it.onTemperatureChanged(value)
        }
    }

    init {
        logI(TAG, "[registerCallback]")
        mHvacManager.registerCallback(this)
    }

    fun release() {
        mHvacManager.unregisterCallback(this)
    }

    fun requestTemperature() {
        logI(TAG, "[requestTemperature]")
        mHvacManager.requestTemperature()
    }

    fun setTemperature(temperature: String?) {
        logI(TAG, "[setTemperature] $temperature")
        if (temperature.isNullOrEmpty()) return
        mHvacManager.setTemperature(temperature.toInt())
    }

    fun setHvacListener(callback: IHvacCallback) {
        logI(TAG, "[setHvacListener] $callback")
        mHvacVMCallback = callback
    }

    fun removeHvacListener(callback: IHvacCallback) {
        logI(TAG, "[removeHvacListener] $callback")
        mHvacVMCallback = null
    }

}