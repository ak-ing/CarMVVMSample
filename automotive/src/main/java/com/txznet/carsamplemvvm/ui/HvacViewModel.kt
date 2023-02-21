package com.txznet.carsamplemvvm.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.txznet.carsamplemvvm.model.HvacRepository
import com.txznet.carsamplemvvm.model.IHvacCallback
import com.txznet.common.utils.logI
import com.txznet.common.vm.BaseViewModel

/**
 * Created by Rick on 2023-01-30  20:53.
 * Description:
 */
class HvacViewModel(private val test: String) : BaseViewModel<HvacRepository>(), IHvacCallback {

    private val mTempLd = MutableLiveData<String>()

    init {
        repository.setHvacListener(this)
        requestTemperature()
    }

    override fun onTemperatureChanged(temp: String) {
        logI("TAG", "[onTemperatureChanged] $temp")
        mTempLd.postValue(temp)
    }


    /**
     * 请求页面数据
     */
    fun requestTemperature() {
        repository.requestTemperature()
    }

    /**
     * 将温度数据设定到Service中
     */
    fun setTemperature(view: View?) {
        repository.setTemperature(getTempLive().value)
    }

    fun getTempLive(): LiveData<String> {
        return mTempLd
    }

}