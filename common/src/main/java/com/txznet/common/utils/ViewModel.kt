package com.txznet.common.utils

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import com.txznet.common.ui.BaseVMActivity
import com.txznet.common.ui.BaseVMFragment
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * Created by Rick on 2023-02-01  12:12.
 * Description: ViewModel参数注入拓展
 */

@JvmField
val DEFAULT_EXTRAS_KEY = object : CreationExtras.Key<Any> {}


@MainThread
public fun <VM : ViewModel> BaseVMActivity<*, *>.viewModel(
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val modelClass: Class<VM> = this.parseTypeClass()

    val factoryPromise = if (factoryProducer != null) {
        factoryProducer
    } else {
        { ParamViewModelFactory() }
    }
    return ViewModelLazy(
        modelClass.kotlin,
        { this.viewModelStore },
        factoryPromise,
        { this.defaultViewModelCreationExtras }
    )
}

@MainThread
public fun <VM : ViewModel> BaseVMFragment<*, *>.viewModel(
    ownerProducer: () -> ViewModelStoreOwner = { this },
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val modelClass: Class<VM> = parseTypeClass()
    val factoryPromise = if (factoryProducer != null) {
        factoryProducer
    } else {
        { ParamViewModelFactory() }
    }
    return ViewModelLazy(
        modelClass.kotlin,
        { ownerProducer().viewModelStore },
        factoryPromise,
        { this.defaultViewModelCreationExtras }
    )
}

@MainThread
public inline fun <reified VM : ViewModel> Fragment.activityViewModel(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val modelClass: Class<VM> = VM::class.java
    val factoryPromise = if (factoryProducer != null) {
        factoryProducer
    } else {
        { ParamViewModelFactory() }
    }
    return ViewModelLazy(
        modelClass.kotlin,
        { requireActivity().viewModelStore },
        factoryPromise,
        { this.defaultViewModelCreationExtras }
    )
}

/**
 * 构造带参ViewModel
 */
class ParamViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val param = extras[DEFAULT_EXTRAS_KEY]
        var newInstance: T
        param?.let {
            val constructor = modelClass.getConstructor(param::class.java)
            constructor.isAccessible = true
            try {
                newInstance = constructor.newInstance(it)
                return newInstance
            } catch (e: InvocationTargetException) {
                e.targetException.printStackTrace()
            }
        }
        newInstance = modelClass.newInstance()
        return newInstance
    }
}

fun <T> Any.parseTypeClass(): Class<T> {
    val modelClass: Class<T>
    val type = this::class.java.genericSuperclass
    modelClass = if (type is ParameterizedType) {
        type.actualTypeArguments.filterIsInstance<Class<*>>()[1]
    } else {
        ViewModel::class.java
    } as Class<T>
    return modelClass
}
