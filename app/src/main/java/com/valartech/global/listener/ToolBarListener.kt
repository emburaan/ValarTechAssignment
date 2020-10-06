package com.valartech.global.listener

import com.valartech.global.utils.DebugLog
import com.valartech.global.utils.TAG

/**
 * Created on 2/2/18.
 */

interface ToolBarListener {

    fun onStartClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }

    fun onEndClicked() {
        DebugLog.d(TAG, "onStartClicked but not handled")
    }
}
