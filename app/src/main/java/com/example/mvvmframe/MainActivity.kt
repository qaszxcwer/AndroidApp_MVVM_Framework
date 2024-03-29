package com.example.mvvmframe

import android.content.Intent
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import com.example.mvvmframe.databinding.ActivityMainBinding
import com.example.mvvmframe.zTest.TestActivity
import com.example.mvvmframe.zTest.TestFragment1
import com.example.mvvmframe.zTest.TestFragmentVM
import com.example.mvvmframe.zTest.TestViewModel
import com.example.mvvmframe.zTest.TestWebActivity
import com.example.mvvmframe.zTest.eventBus.LoginEvent
import com.jeremyliao.liveeventbus.LiveEventBus
import person.qaszxcwer.appbaseframe.activity.BaseActivity
import person.qaszxcwer.appbaseframe.activity.BaseVMActivity
import person.qaszxcwer.appbaseframe.extend.immerse
import person.qaszxcwer.appbaseframe.extend.notNull
import person.qaszxcwer.appbaseframe.utils.LogUtils
import person.qaszxcwer.appbaseframe.utils.ToastUtils

class MainActivity : BaseVMActivity<ActivityMainBinding, TestViewModel>() {
    // by lazy形式示例，可用binding.txtTest代替
    private val txtTest: TextView by lazy { findViewById<TextView>(R.id.txtTest) }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initParams(intent: Intent) {
    }

    override fun initView() {
        immerse(type = WindowInsetsCompat.Type.systemBars(),statusBarBlack = true, navigationIsBlack = true)
        notNull(this, this) {
            LogUtils.i("notNull不为空")
        }
        changeFragment()
        txtTest.text = BuildConfig.FLAVOR
        txtTest.setOnClickListener {
//            startActivity(Intent(mContext, TestActivity::class.java))
            startActivity(Intent(mContext, TestWebActivity::class.java))
//            changeFragment()
        }
    }

    override fun initData() {
        LiveEventBus
            .get(LoginEvent::class.java)
            .observe(this) {
                ToastUtils.showLong("收到了登录消息${it.content}")
            }
        viewModel.observeTestSameVM(this) {
            ToastUtils.showLong("收到了VM中转的fragment消息${it}")
        }
    }

    override fun getViewModel(): Class<TestViewModel> {
        return TestViewModel::class.java
    }

    private var first: Boolean = false
    private val fragment1 by lazy { TestFragment1() }
    private val fragment2 by lazy { TestFragmentVM() }

    private fun changeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.hide(fragment1)
        fragmentTransaction.hide(fragment2)
        val fragment = if (first) fragment1 else fragment2
        if (!fragment.isAdded) {
            fragmentTransaction.add(R.id.frameLayout, fragment)
        }
        fragmentTransaction.show(fragment)
        fragmentTransaction.commit()
        first = !first
    }
}