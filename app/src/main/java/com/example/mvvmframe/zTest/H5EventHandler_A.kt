package com.example.mvvmframe.zTest

import android.content.Intent
import androidx.activity.result.ActivityResult
import person.qaszxcwer.appbaseframe.hybrid.BaseHybridEventHandler
import person.qaszxcwer.appbaseframe.hybrid.IActivityResultHandler
import person.qaszxcwer.appbaseframe.hybrid.IHybrid
import person.qaszxcwer.appbaseframe.hybrid.NativeEventHandler
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/25
 * 
 */
class H5EventHandler_A : BaseHybridEventHandler(), IActivityResultHandler {
    companion object {
        private const val launchCode = 1
    }
    init {
        LogUtils.d("我是 $this")
    }

    override fun getOperationType(): String {
        return HybridEventEnum.A.name
    }

    override fun onHandleEvent(params: String): String {
        LogUtils.d("$this A的处理 params = $params")
        getHybridImpl()?.run {
            val intent = Intent(activityRef.get(), TestResultActivity::class.java)
            launchActivityResultLauncher(getLaunchCode(), intent)
        }
        return "123"
    }

    override fun getLaunchCode(): Int {
        return launchCode
    }

    override fun onLaunchActivityResult(activityResult: ActivityResult) {
        with(activityResult) {
            LogUtils.d("handler收到了activity结果：$resultCode, $data")
            LogUtils.d("result：${data?.getStringExtra(TestResultActivity.KEY_Result)}")
            getHybridImpl()?.let {
                NativeEventHandler.callJs(it, HybridEventEnum.A.name, "123" )
                val map = HashMap<String, Any>()
                map["abc"] = 123
                map["456"] = "def"
                NativeEventHandler.callJs(it, HybridEventEnum.B.name, map )
            }
        }
    }
}