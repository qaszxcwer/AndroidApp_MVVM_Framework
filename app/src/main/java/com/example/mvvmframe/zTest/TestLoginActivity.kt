package com.example.mvvmframe.zTest

import com.example.mvvmframe.databinding.ActivityTestLoginBinding
import person.qaszxcwer.appbaseframe.activity.BaseActivity

/**
 *
 * @author 顾荣林
 * 2023/6/12
 */
class TestLoginActivity: BaseActivity<ActivityTestLoginBinding>() {
    override fun getViewBinding(): ActivityTestLoginBinding {
        return ActivityTestLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
    }
}