package com.txznet.carsamplemvvm.ui

import com.txznet.carsamplemvvm.R
import com.txznet.carsamplemvvm.databinding.ActivityMainBinding
import com.txznet.common.ui.BaseVMActivity

class HvacActivity : BaseVMActivity<ActivityMainBinding, HvacViewModel>(R.layout.activity_main) {
    override fun getVMExtras(): Any? = "test"

    override fun ActivityMainBinding.initView() {
    }

    override fun ActivityMainBinding.initObservable() {

    }


}