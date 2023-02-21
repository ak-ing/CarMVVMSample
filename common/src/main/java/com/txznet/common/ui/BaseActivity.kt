package com.txznet.common.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.logV

/**
 * Created by Rick on 2023-01-30  17:36.
 * Description:
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val TAG = CLASS_TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logV(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        logV(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        logV(TAG, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logV(TAG, "onSaveInstanceState")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logV(TAG, "onNewIntent")
    }

    override fun onResume() {
        super.onResume()
        logV(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        logV(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        logV(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logV(TAG, "onDestroy")
    }


}