package person.qaszxcwer.appbaseframe.hybrid

import androidx.activity.result.ActivityResult

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