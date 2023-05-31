package com.example.mvvmframe.zTest

import android.text.TextUtils
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import person.qaszxcwer.appbaseframe.hybrid.BaseHybridEventHandler
import person.qaszxcwer.appbaseframe.hybrid.HybridEvent
import person.qaszxcwer.appbaseframe.hybrid.HybridEventHandlerManager
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/25
 * 
 */
class TestJsInterface(private val activity: AppCompatActivity, private val fragment: Fragment? = null) {
    /**
     * H5调App的例子
     */
    @JavascriptInterface
    fun jsEvent(jsString: String): String? {
        LogUtils.d("收到的H5字符串为：$jsString")
        if (TextUtils.isEmpty(jsString)) {
            LogUtils.e("H5消息不得为空")
            return null
        }
        val event = HybridEvent(jsString)
        LogUtils.d("event转化的json是：${event.toJsonString()}")
        val handler: BaseHybridEventHandler? = HybridEventHandlerManager[event.operationType]
        handler?.let {
            LogUtils.d("处理event的是：$it")
            return it.preHandleEvent(activity, fragment, event.params)
        }
        LogUtils.e("找不到对应的handler处理H5消息")
        return null
    }
}