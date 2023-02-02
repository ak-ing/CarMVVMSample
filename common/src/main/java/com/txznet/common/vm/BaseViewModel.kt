package com.txznet.common.vm

import androidx.lifecycle.ViewModel
import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.LogUtil.TAG_COMMON
import com.txznet.common.utils.getInstance
import com.txznet.common.utils.logV

/**
 * Created by Rick on 2023-01-30  17:00.
 * Description:只需要将Model的实例传入，方便 ViewModel 的实现类调用
 */
abstract class BaseViewModel<M : BaseRepository> : ViewModel() {

    protected val TAG = TAG_COMMON + CLASS_TAG
    protected val repository: M = getInstance(0)!!

    override fun onCleared() {
        super.onCleared()
        logV(TAG, "[onCleared]")
    }

}

