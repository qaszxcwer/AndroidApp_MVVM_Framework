package person.qaszxcwer.appbaseframe.hybrid

import androidx.activity.result.ActivityResult

/**
 *
 * date: 2023/5/26
 * 
 * 如果handler需要启动另一个activity并收到返回值进行处理，则实现这个接口，以便分发返回值
 */
interface IActivityResultHandler {
    fun getLaunchCode(): Int
    fun onLaunchActivityResult(activityResult: ActivityResult)
}