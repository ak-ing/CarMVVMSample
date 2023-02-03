package com.txznet.sdk.hvac

/**
 * Created by Rick on 2023-02-03  17:08.
 */
fun interface IHvacCallback {

    fun onTemperatureChanged(temperature: Double)

}