package com.example.mvvmframe.zTest

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.mvvmframe.databinding.ActivityTestLoginBinding
import com.example.mvvmframe.zTest.eventBus.LoginEvent
import com.jeremyliao.liveeventbus.LiveEventBus
import person.qaszxcwer.appbaseframe.activity.BaseActivity
import java.lang.ref.WeakReference

/**
 *
 * 2023/6/12
 */
class TestLoginActivity: BaseActivity<ActivityTestLoginBinding>() {
    override fun getViewBinding(): ActivityTestLoginBinding {
        return ActivityTestLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
        val handler = LoginHandler(this)
        handler.sendEmptyMessageDelayed(1, 2000)
    }

    private fun loginSucc() {
        LiveEventBus.get(LoginEvent::class.java).post(LoginEvent("***登录消息***"))
        finish()
    }

    class LoginHandler(activity: TestLoginActivity): Handler(Looper.getMainLooper()) {
        private val activityRef: WeakReference<TestLoginActivity> = WeakReference(activity)
        override fun handleMessage(msg: Message) {
            val activity = activityRef.get()
            activity?.loginSucc()
        }
    }
}