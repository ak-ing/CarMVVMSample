package com.txznet.common.vm

import androidx.lifecycle.ViewModel
import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.logV
import com.txznet.common.utils.typeInstance

/**
 * Created by Rick on 2023-01-30  17:00.
 * Description:只需要将Model的类型传入，方便 ViewModel 的实现类调用
 */
abstract class BaseViewModel<M : BaseRepository> : ViewModel() {

    protected val TAG = CLASS_TAG
    protected val repository: M by lazy { this.typeInstance(0)!! }

    init {
        this.addCloseable(repository)
    }

    override fun onCleared() {
        super.onCleared()
        logV(TAG, "[onCleared]")
    }

}

