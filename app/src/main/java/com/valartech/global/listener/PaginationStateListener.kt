package com.valartech.global.listener

import com.valartech.global.enumeration.State

interface PaginationStateListener {
    fun setState(newState: State)
}
