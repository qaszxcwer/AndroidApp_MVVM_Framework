package person.qaszxcwer.appbaseframe.hybrid

import android.content.Intent

/**
 *
 * date: 2023/5/26
 * 
 */
interface IHybrid {
    /**
     * 建议实现，如果确认需求中不存在获取activityResult的情况也可以不实现
     */
    fun launchActivityResultLauncher(launchCode: Int, intent: Intent)

    /**
     * 建议实现，如果确认需求中不存在调用H5方法的情况，也可以不实现<BR>
     * 通知activity去loadUrl而不是让activity提供webview的目的是把webview的具体实现和框架隔离<BR>
     * 框架不在乎用的哪个webview来浏览H5
     */
    fun loadUrlCallJs(urlJsFun: String)
}