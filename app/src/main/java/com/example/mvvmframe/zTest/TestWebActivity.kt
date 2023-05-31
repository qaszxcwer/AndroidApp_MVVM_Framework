package com.example.mvvmframe.zTest

import android.content.Intent
import android.text.TextUtils
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mvvmframe.databinding.ActivityTestWebBinding
import person.qaszxcwer.appbaseframe.activity.BaseActivity
import person.qaszxcwer.appbaseframe.extend.immerse
import person.qaszxcwer.appbaseframe.hybrid.HybridEventHandlerManager
import person.qaszxcwer.appbaseframe.hybrid.IHybrid
import person.qaszxcwer.appbaseframe.utils.LogUtils
import person.qaszxcwer.appbaseframe.utils.ToastUtils

/**
 *
 * date: 2023/5/6
 * 
 * 一个简单的web页面，模拟一下H5和原生互相调用的场景
 */
class TestWebActivity: BaseActivity<ActivityTestWebBinding>(), IHybrid {

    override fun getViewBinding(): ActivityTestWebBinding {
        return ActivityTestWebBinding.inflate(layoutInflater)
    }

    override fun initParams(intent: Intent) {
        with(HybridEventHandlerManager) {
            addHandler(H5EventHandler_A())
            addHandler(H5EventHandler_B())
        }
    }

    private val jsInterface = TestJsInterface(this)

    override fun initView() {
        immerse()
        binding.txtA.setOnClickListener{
            sendH5Msg("A")
        }
        binding.txtB.setOnClickListener{
            sendH5Msg("B")
        }
        binding.txtC.setOnClickListener{
            sendH5Msg("C")
        }
        binding.txtEmpty.setOnClickListener{
            sendH5Msg(null)
        }
        val webSettings = binding.webView.settings
        // 一般在业务中使用第三方SDK的浏览器，这里只是用自带浏览器示例一下，所以为了首页面能展示正常就把JS开了，HTTP加载资源也开了
        webSettings.javaScriptEnabled = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (view == null || request == null) {
                    return super.shouldOverrideUrlLoading(view, request)
                }
                view.loadUrl(request.url.toString())
                return true
            }
        }
        binding.webView.addJavascriptInterface(jsInterface, "Android")
    }

    override fun initData() {
        binding.webView.loadUrl("https://www.baidu.com")
    }

    private fun sendH5Msg(operationType : String?) {
        if (TextUtils.isEmpty(operationType)) {
            jsInterface.jsEvent("")
            return
        }
        val stringFormat = "{\n" +
                "  \"operationType\": \"%s\",\n" +
                "  \"params\": \"aaaaaaabbbbbc\"\n" +
                "}"
        val string = String.format(stringFormat, operationType)
        jsInterface.jsEvent(string)
    }

    private val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        it?.run {
            LogUtils.d("收到了activity结果：$resultCode, $data")
            LogUtils.d("result：${data?.getStringExtra(TestResultActivity.KEY_Result)}")
            HybridEventHandlerManager.onLaunchActivityResult(launchCode, it)
        }
    }

    private var launchCode: Int = 0

    override fun launchActivityResultLauncher(launchCode: Int, intent: Intent) {
        this.launchCode = launchCode
        activityResult.launch(intent)
    }

    override fun loadUrlCallJs(urlJsFun: String) {
        LogUtils.d("activity试图loadUrl：$urlJsFun")
        ToastUtils.showLong("activity试图loadUrl：$urlJsFun")
        binding.webView.loadUrl(urlJsFun)
    }
}