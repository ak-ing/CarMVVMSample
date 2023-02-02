package com.txznet.common.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import com.txznet.common.utils.DEFAULT_EXTRAS_KEY
import com.txznet.common.utils.binding
import com.txznet.common.utils.viewModel
import com.txznet.common.vm.BaseViewModel

/**
 * Created by Rick on 2023-01-30  20:22.
 * Description:
 */
abstract class BaseVMFragment<V : ViewDataBinding, VM : BaseViewModel<*>>(@LayoutRes layoutRes: Int) :
    BaseFragment(layoutRes) {

    protected val binding: V by binding()
    protected val vm: VM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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