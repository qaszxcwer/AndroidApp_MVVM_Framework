package person.qaszxcwer.appbaseframe.hybrid

import com.google.gson.Gson
import org.json.JSONObject

/**
 *
 * date: 2023/5/25
 * 
 * 和H5交互的事件
 */
class HybridEvent {
    companion object {
        private const val OperationType: String = "operationType"
        private const val Params: String = "params"
    }

    val operationType: String

    val params: String

    constructor(operationType: String, params: String) {
        this.operationType = operationType
        this.params = params
    }

    constructor(jsString: String) {
        val jsObj: JSONObject = JSONObject(jsString)
        this.operationType = jsObj.getString(OperationType)
        this.params = jsObj.getString(Params)
    }

    fun toJsonString(): String {
        return Gson().toJson(this)
    }
}