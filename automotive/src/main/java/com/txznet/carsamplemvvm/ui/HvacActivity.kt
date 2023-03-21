package com.txznet.carsamplemvvm.ui

import android.view.View
import com.txznet.carsamplemvvm.BR
import com.txznet.carsamplemvvm.R
import com.txznet.carsamplemvvm.databinding.ActivityMainBinding
import com.txznet.common.ui.BaseVMActivity
import com.txznet.common.utils.logI

class HvacActivity : BaseVMActivity<ActivityMainBinding, HvacViewModel>(R.layout.activity_main) {
    override fun getVMExtras(): Any? = "参数注入"

    override fun ActivityMainBinding.initView() {
        bindVariables(BR.viewModel to vm, BR.click to ClickProxy())
    }

    override fun ActivityMainBinding.initObservable() {
        //测试打印
        vm.getTempLive().observe(this@HvacActivity) {
            logI(TAG, "[initObservable]：$it")
        }
    }

    inner class ClickProxy {
        fun changeTemp(v: View) {
            vm.setTemperature()
        }
    }

}