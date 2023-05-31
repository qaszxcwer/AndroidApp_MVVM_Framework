package com.example.mvvmframe.zTest

import person.qaszxcwer.appbaseframe.hybrid.BaseHybridEventHandler
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/25
 * 
 */
class H5EventHandler_B: BaseHybridEventHandler() {
    init {
        LogUtils.d("我是 $this")
    }

    override fun getOperationType(): String {
        return HybridEventEnum.B.name
    }

    override fun onHandleEvent(params: String): String {
        LogUtils.d("$this B的处理 params = $params")
        return "123"
    }
}