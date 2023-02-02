package com.txznet.common.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.LogUtil
import com.txznet.common.utils.logV

/**
 * Created by Rick on 2023-01-30  17:28.
 * Description:
 */
abstract class BaseAndroidViewModel<M : BaseRepository>(app: Application, val mRepository: M) :
    AndroidViewModel(app) {

    protected val TAG = LogUtil.TAG_COMMON + CLASS_TAG

    override fun onCleared() {
        super.onCleared()
        logV(TAG, "[onCleared]")
    }
}