package com.example.mvvmframe.zTest

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.core.view.WindowInsetsCompat
import com.example.mvvmframe.databinding.ActivityMainBinding
import person.qaszxcwer.appbaseframe.activity.BaseVMActivity
import person.qaszxcwer.appbaseframe.extend.immerse
import person.qaszxcwer.appbaseframe.utils.DeviceUtils
import person.qaszxcwer.appbaseframe.utils.LogUtils
import java.lang.ref.WeakReference

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
class TestActivity: BaseVMActivity<ActivityMainBinding, TestViewModel>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): Class<TestViewModel> {
        return TestViewModel::class.java
    }

    override fun onVMError(t: Throwable) {
        LogUtils.e("activity收到了请求错误")
    }

    override fun initParams(intent: Intent) {
        val size = DeviceUtils.getScreenSize(this)
        LogUtils.d("width = ${size[0]}, height = ${size[1]}")
        val size2 = DeviceUtils.getScreenSizeReal(this)
        LogUtils.d("width = ${size2[0]}, height = ${size2[1]}")
    }

    private val handler = TestHandler(this)

    override fun initView() {
        immerse(type = WindowInsetsCompat.Type.systemBars(),statusBarBlack = true, navigationIsBlack = true)
        handler.sendEmptyMessageDelayed(1, 10000)
        binding.txtTest.setOnClickListener() {
            TestRotatedFullScreenDialog().show(supportFragmentManager)
            TestDialog().show(supportFragmentManager)
        }
    }

    override fun initData() {
        viewModel.observeIpData(this, {
            LogUtils.d("activity收到了ip接口返回")
            LogUtils.d("$it")
            binding.txtTest.text = "$it"
        })
        viewModel.observeNameList(this, {
            LogUtils.d("activity收到了name接口返回")
            LogUtils.d("$it")
        })
        viewModel.getIp()
        viewModel.getNameList()
    }

    private fun changeText(string: String) {
        binding.txtTest.text = string
    }

    // msg handler示例，kotlin默认是静态内部类，所以不会持有外部引用
    class TestHandler(activity: TestActivity): Handler(Looper.getMainLooper()) {
        private val weakReference: WeakReference<TestActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            val activity = weakReference.get()
            activity?.changeText("sfaf$msg")
        }
    }
}