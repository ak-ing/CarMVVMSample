package com.txznet.common.utils


import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

/**
 * 延迟扩展数据绑定布局，调用 [Activity.setContentView]、设置生命周期所有者并返回绑定的委托。
 */
class ContentViewBindingDelegate<in A : AppCompatActivity, out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) {

    private var binding: T? = null

    operator fun getValue(activity: A, property: KProperty<*>): T {
        if (binding == null) {
            binding = DataBindingUtil.setContentView<T>(activity, layoutRes).apply {
                lifecycleOwner = activity
            }
        }
        return binding!!
    }
}

fun <A : AppCompatActivity, T : ViewDataBinding> AppCompatActivity.contentView(
    @LayoutRes layoutRes: Int
): ContentViewBindingDelegate<A, T> = ContentViewBindingDelegate(layoutRes)