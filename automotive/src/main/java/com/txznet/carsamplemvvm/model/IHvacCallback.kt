package com.txznet.carsamplemvvm.model

/**
 * Created by Rick on 2023-02-06  11:51.
 * Description: 空调温度String类型回调
 */
fun interface IHvacCallback {
    fun onTemperatureChanged(temp: String)
}