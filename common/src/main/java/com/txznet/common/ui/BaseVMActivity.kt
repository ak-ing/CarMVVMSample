package com.txznet.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.txznet.common.utils.DEFAULT_EXTRAS_KEY
import com.txznet.common.utils.contentView
import com.txznet.common.utils.viewModel
import com.txznet.common.vm.BaseViewModel

/**
 * Created by Rick on 2023-01-30  19:24.
 * Description:
 */
abstract class BaseVMActivity<V : ViewDataBinding, VM : BaseViewModel<*>>(@LayoutRes layoutRes: Int) :
    BaseActivity() {

    protected val binding: V by contentView(layoutRes)
    protected val vm: VM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.initView()
        binding.initObservable()
    }

    @MainThread
    fun bindVariables(vararg args: Pair<Int, Any>) {
        for (arg in args) {
            binding.setVariable(arg.first, arg.second)
        }
        binding.executePendingBindings()
    }

    /**
     * 注入ViewModel参数
     */
    protected abstract fun getVMExtras(): Any?

    protected abstract fun V.initView()

    protected abstract fun V.initObservable()

    override fun getDefaultViewModelCreationExtras(): CreationExtras {
        return MutableCreationExtras(super.getDefaultViewModelCreationExtras()).apply {
            getVMExtras()?.let { set(DEFAULT_EXTRAS_KEY, it) }
        }
    }
}
