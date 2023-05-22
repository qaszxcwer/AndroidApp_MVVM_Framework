package com.example.mvvmframe.zTest

import android.view.LayoutInflater
import com.example.mvvmframe.databinding.FragmentMain1Binding
import person.qaszxcwer.appbaseframe.fragment.BaseFragment
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/12
 * author: GuRongLin
 */
class TestFragment1: BaseFragment<FragmentMain1Binding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentMain1Binding {
        return FragmentMain1Binding.inflate(inflater)
    }

    override fun initView() {
        LogUtils.i("TestFragment1")
        binding.txtTest.setText("1")
    }

    override fun initData() {
    }
}