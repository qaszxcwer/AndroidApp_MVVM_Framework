package person.qaszxcwer.appbaseframe.hybrid

import com.google.gson.Gson

/**
 *
 * date: 2023/5/26
 * 
 */
object NativeEventHandler {
    // 此处假设H5接收原生调用的方法为nativeEvent
    private val JsCallFormat = "javascript:nativeEvent(%s)"

    fun callJs(hybrid: IHybrid, operationType: String, params: String) {
        callJs(hybrid, HybridEvent(operationType, params))
    }

    fun callJs(hybrid: IHybrid, operationType: String, params: Map<String, Any>) {
        callJs(hybrid, HybridEvent(operationType, Gson().toJson(params)))
    }

    fun callJs(hybrid: IHybrid, event: HybridEvent) {
        val url = String.format(JsCallFormat, event.toJsonString())
        hybrid.loadUrlCallJs(url)
    }
}