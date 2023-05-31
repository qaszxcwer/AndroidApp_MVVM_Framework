package person.qaszxcwer.appbaseframe.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import person.qaszxcwer.appbaseframe.dialog.loading.UsualLoadingDialogUtil

/**
 *
 * date: 2023/5/6
 * 
 */
abstract class BaseActivity<T: ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        binding = getViewBinding()
        doBeforeSetContentView()
        setContentView(binding.root)
        initParams(intent)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseResource()
    }

    protected open fun doBeforeSetContentView() {}

    protected abstract fun getViewBinding(): T

    protected open fun initParams(intent: Intent) {}

    protected abstract fun initView()

    protected abstract fun initData()

    protected fun showLoading() {
        UsualLoadingDialogUtil.instance.show(supportFragmentManager)
    }

    protected fun dismissLoading() {
        UsualLoadingDialogUtil.instance.dismiss()
    }

    protected fun openLogin() {
        TODO("自行实现跳转登录，这个和业务强相关，就不在框架里面写了")
    }

    /**
     * 释放资源
     */
    private fun releaseResource() {
        dismissLoading()
        onReleaseResource()
    }

    protected open fun onReleaseResource() {}
}