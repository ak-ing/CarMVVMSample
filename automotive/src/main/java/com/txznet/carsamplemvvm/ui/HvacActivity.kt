package com.txznet.carsamplemvvm.ui

import android.widget.Toast
import com.txznet.carsamplemvvm.R
import com.txznet.carsamplemvvm.databinding.ActivityMainBinding
import com.txznet.common.AppGlobal
import com.txznet.common.ui.BaseVMActivity
import com.txznet.common.utils.logI

class HvacActivity : BaseVMActivity<ActivityMainBinding, HvacViewModel>(R.layout.activity_main) {
    override fun getVMExtras(): Any? = "test"

    override fun ActivityMainBinding.initView() {
        viewModel = vm
    }

    override fun ActivityMainBinding.initObservable() {
        Toast.makeText(AppGlobal.context, "aaaaaaa", Toast.LENGTH_SHORT).show()
        vm.getTempLive().observe(this@HvacActivity) {
            logI(TAG, "[initObservable]：$it")
        }
    }

}