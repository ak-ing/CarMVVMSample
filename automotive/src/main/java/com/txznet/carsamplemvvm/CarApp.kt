package com.txznet.carsamplemvvm

import android.app.Application
import com.txznet.common.AppGlobal
import com.txznet.sdk.hvac.HvacManager

/**
 * Created by Rick on 2023-01-30  21:10.
 * Description:
 */
class CarApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this)
    }

}