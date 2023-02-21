package com.txznet.carsamplemvvm.model

import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.logI
import com.txznet.common.utils.logV
import com.txznet.sdk.sample.DefaultHvacCallback
import com.txznet.sdk.sample.HvacManager

/**
 * Created by Rick on 2023-01-30  20:54.
 * Description:
 */
class HvacRepository : BaseRepository(), DefaultHvacCallback {

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
        HvacManager.instant.registerCallback(this)
    }

    fun requestTemperature() {
        logI(TAG, "[requestTemperature]")
        HvacManager.instant.requestTemperature()
    }

    fun setTemperature(temperature: String?) {
        logI(TAG, "[setTemperature] $temperature")
        if (temperature.isNullOrEmpty()) return
        HvacManager.instant.setTemperature(temperature.toInt())
    }

    fun setHvacListener(callback: IHvacCallback) {
        logI(TAG, "[setHvacListener] $callback")
        mHvacVMCallback = callback
    }

    fun removeHvacListener(callback: IHvacCallback) {
        logI(TAG, "[removeHvacListener] $callback")
        mHvacVMCallback = null
    }

    override fun close() {
        super.close()
        mHvacVMCallback?.let { removeHvacListener(it) }
        HvacManager.instant.unregisterCallback(this)
    }

}