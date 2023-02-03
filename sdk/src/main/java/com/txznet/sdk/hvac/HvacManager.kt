package com.txznet.sdk.hvac

import android.os.IBinder
import com.txznet.sdk.HvacCallback
import com.txznet.sdk.HvacInterface
import com.txznet.sdk.base.BaseConnectManager
import com.txznet.sdk.util.RemoteHelper
import com.txznet.sdk.util.SdkLogUtils
import com.txznet.sdk.util.logV

/**
 * Created by Rick on 2023-02-03  17:10.
 * Description:
 */
class HvacManager : BaseConnectManager<HvacInterface>() {
    companion object {
        private const val TAG = SdkLogUtils.TAG_SDK + "HvacManager"
        private const val SERVICE_PACKAGE = "com.fwk.service"
        private const val SERVICE_CLASSNAME = "com.fwk.service.SimpleService"

        val instant by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { HvacManager() }
    }

    private val mCallbacks: ArrayList<IHvacCallback> = arrayListOf()

    private val mSampleCallback: HvacCallback.Stub = object : HvacCallback.Stub() {
        override fun onTemperatureChanged(temperature: Double) {
            logV(TAG, "[onTemperatureChanged] $temperature")
            getMainHandler().post {
                for (callback in mCallbacks) {
                    callback.onTemperatureChanged(temperature)
                }
            }
        }
    }

    override fun getServicePkgName(): String = SERVICE_PACKAGE

    override fun getServiceClassName(): String = SERVICE_CLASSNAME

    override fun asInterface(service: IBinder?): HvacInterface {
        return HvacInterface.Stub.asInterface(service)
    }

    /******************/

    fun requestTemperature() {
        RemoteHelper.tryExec {
            if (isServiceConnected(true)) {
                getProxy().requestTemperature()
            } else {
                getTaskQueue().offer(this::requestTemperature)
            }
        }
    }

    fun setTemperature(temperature: Int) {
        RemoteHelper.tryExec {
            if (isServiceConnected(true)) {
                getProxy().setTemperature(temperature)
            } else {
                getTaskQueue().offer { setTemperature(temperature) }
            }
        }
    }


}