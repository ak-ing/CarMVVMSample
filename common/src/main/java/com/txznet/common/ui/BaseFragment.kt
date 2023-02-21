package com.txznet.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.txznet.common.utils.CLASS_TAG
import com.txznet.common.utils.logV

/**
 * Created by Rick on 2023-01-30  20:15.
 * Description:
 */
abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    protected val TAG = CLASS_TAG

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logV(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logV(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logV(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logV(TAG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        logV(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        logV(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        logV(TAG, "onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logV(TAG, "onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        logV(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        logV(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        logV(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        logV(TAG, "onDetach")
    }

}