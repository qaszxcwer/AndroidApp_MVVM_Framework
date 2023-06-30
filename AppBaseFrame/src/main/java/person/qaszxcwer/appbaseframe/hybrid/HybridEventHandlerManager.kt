package person.qaszxcwer.appbaseframe.hybrid

import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 *
 * date: 2023/5/25
 * 
 */
object HybridEventHandlerManager:LinkedHashMap<String, BaseHybridEventHandler>() {
    /**
     * LinkedHashMap有序、key不可重复,所以同一个handler类的实例重复添加会覆盖，不会有多个同时在map中<BR>
     * 以operationType为key，便于直接取出
     */
    fun addHandler(handler: BaseHybridEventHandler) {
        put(handler.getOperationType(), handler)
    }

    /**
     * 处理H5发来的信息
     */
    fun handleJsEvent(jsString: String, activity: AppCompatActivity, fragment: Fragment? = null): String? {
        val event = HybridEvent(jsString)
        val handler: BaseHybridEventHandler? = HybridEventHandlerManager[event.operationType]
        handler?.let {
            return it.preHandleEvent(activity, fragment, event.params)
        }
        return null
    }

    /**
     * 收到activityResult之后调用，内部进行分发
     */
    fun onLaunchActivityResult(launchCode: Int, result: ActivityResult) {
        if (launchCode <= 0) {
            return
        }
        val iterator = entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val value = entry.value
            if (value is IActivityResultHandler && launchCode == value.getLaunchCode()) {
                value.onLaunchActivityResult(result)
                break
            }
        }
    }
}