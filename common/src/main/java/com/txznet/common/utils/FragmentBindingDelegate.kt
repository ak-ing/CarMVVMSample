package com.txznet.common.utils


import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.reflect.KProperty

/**
 * 绑定fragment布局View，设置生命周期所有者并返回binding,自动销毁
 */
class FragmentViewBindingDelegate<in F : Fragment, out T : ViewDataBinding> {

    private var binding: T? = null

    operator fun getValue(fragment: F, property: KProperty<*>): T {
        binding?.let { return it }

        fragment.view ?: throw IllegalArgumentException("The fragment view is empty or has been destroyed")

        binding = DataBindingUtil.bind<T>(fragment.requireView())?.also {
            it.lifecycleOwner = fragment.viewLifecycleOwner
        }

        fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(Clear(fragment), false)

        return binding!!
    }

    inner class Clear(private val thisRef: F) : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            if (thisRef === f) {
                binding = null
                fm.unregisterFragmentLifecycleCallbacks(this)
            }
        }
    }

}


fun <F : Fragment, T : ViewDataBinding> Fragment.binding(): FragmentViewBindingDelegate<F, T> =
    FragmentViewBindingDelegate()