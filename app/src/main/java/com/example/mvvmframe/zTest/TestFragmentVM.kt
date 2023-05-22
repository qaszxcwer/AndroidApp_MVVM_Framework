package com.example.mvvmframe.zTest

import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mvvmframe.databinding.FragmentMain2Binding
import person.qaszxcwer.appbaseframe.fragment.BaseFragment
import person.qaszxcwer.appbaseframe.fragment.BaseVMFragment
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/12
 * author: GuRongLin
 */
class TestFragmentVM: BaseVMFragment<TestViewModel, FragmentMain2Binding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentMain2Binding {
        return FragmentMain2Binding.inflate(inflater)
    }

    override fun getViewModel(): Class<TestViewModel> {
        return TestViewModel::class.java
    }

    override fun onVMError(t: Throwable) {
        LogUtils.e("fragment收到了请求错误")
    }

    override fun initView() {
        LogUtils.i("TestFragment2")
        binding.txtTest.setText("2")
        Glide.with(this)
            .load("https://fanyi.youdao.com/img/logo.6ed1c44b.png")
            .centerCrop()
            .into(binding.imgTest)
        Glide.with(this)
            .load("https://fanyi.youdao.com/img/logo.6ed1c44b.png")
            .centerCrop()
            .into(binding.imgTest2)
    }

    override fun initData() {
        viewModel.observeIpData(this, Observer { it ->
            LogUtils.d("fragment收到了ip接口返回")
            LogUtils.d("$it")
            binding.txtTest.text = "$it"
        })
        viewModel.getIp()
    }
}