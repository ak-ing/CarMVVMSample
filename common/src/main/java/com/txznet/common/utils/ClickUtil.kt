package com.txznet.common.utils

import android.annotation.SuppressLint
import android.view.View
import kotlinx.coroutines.Runnable

fun View.applyDebouncing(
    listener: View.OnClickListener,
    mDuration: Long = OnDebouncingClickListener.DEBOUNCING_DEFAULT_VALUE,
    withEnable: Boolean = false,
    countdownCallback: OnDebouncingClickListener.OnCountdownCallback? = null
) {
    this.setOnClickListener(object : OnDebouncingClickListener(mDuration, withEnable, countdownCallback) {
        override fun onDebouncingClick(v: View?) {
            listener.onClick(v)
        }
    })
}

/**
 * Created by Rick on 2023-01-08  16:46.
 * Description: 点击防抖
 * @param mDuration 间隔时间
 * @param withEnable 是否改变view的Enable状态
 * @param countdownCallback 倒计时回调 1000毫秒间隔
 */
abstract class OnDebouncingClickListener @JvmOverloads constructor(
    val mDuration: Long = DEBOUNCING_DEFAULT_VALUE,
    val withEnable: Boolean = false,
    val countdownCallback: OnCountdownCallback? = null
) : View.OnClickListener {

    /**
     * 倒计时回调
     */
    interface OnCountdownCallback {
        fun countdown(timeLeft: Long)
    }

    private var mEnabled = true

    abstract fun onDebouncingClick(v: View?)

    @SuppressLint("LongLogTag")
    override fun onClick(v: View) {
        if (mEnabled) {
            mEnabled = false
            onDebouncingClick(v)
            if (withEnable) {
                v.isEnabled = mEnabled
            }
            v.postDelayed(Runnable {
                mEnabled = true
                if (withEnable) {
                    v.isEnabled = true
                }
            }, mDuration)

            countdownCallback?.let {
                v.post(object : Runnable {
                    private var timeLeft = mDuration
                    override fun run() {
                        countdownCallback.countdown(timeLeft)
                        if (timeLeft >= 1000) {
                            v.postDelayed(this::run, 1000)
                        }
                        timeLeft -= 1000
                    }
                })
            }
        } else {
            logV(TAG, "onClick :${v.id} is disable.")
        }
    }

    companion object {
        private const val TAG = "OnDebouncingClickListener"
        const val DEBOUNCING_DEFAULT_VALUE: Long = 700
    }
}