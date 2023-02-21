package com.txznet.common.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.txznet.common.model.BaseRepository
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.logV
import com.txznet.common.utils.typeInstance

/**
 * Created by Rick on 2023-01-30  17:28.
 * Description:
 */
abstract class BaseAndroidViewModel<M : BaseRepository>(app: Application) : AndroidViewModel(app) {

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