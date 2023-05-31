package person.qaszxcwer.appbaseframe.hybrid

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

/**
 * jsString 格式要求如下,和HybridEvent伴生对象中的字段对应
 * {
 * "operationType": "XXXX", //和H5约定的操作符
 * "params": { // 业务数据
 * "abc": "asdfasdfadf", // 这些不用对应
 * "bcd": "sdfasdga"
 * ...
 * }
 */

/**
 *
 * date: 2023/5/25
 * 
 * H5里面，js调用原生方法的事件基类，具体逻辑写在子类中<BR>
 * 对js发来数据的格式有要求：<BR>
 *
 */
abstract class BaseHybridEventHandler {
    protected lateinit var activityRef: WeakReference<AppCompatActivity>
    protected lateinit var fragmentRef: WeakReference<Fragment>

    /**
     * 强制子类重写匹配字段
     */
    abstract fun getOperationType(): String

    fun preHandleEvent(activity: AppCompatActivity, fragment: Fragment?, params: String): String {
        this.activityRef = WeakReference(activity)
        this.fragmentRef = WeakReference(fragment)
        return onHandleEvent(params)
    }

    /**
     * 强制子类重写执行方法
     */
    abstract fun onHandleEvent(params: String): String

    fun getHybridImpl(): IHybrid? {
        val fragment = fragmentRef.get()
        if (fragment != null && fragment is IHybrid) {
            return fragment
        }
        val activity = activityRef.get()
        if (activity is IHybrid) {
            return activity
        }
        return null
    }
}