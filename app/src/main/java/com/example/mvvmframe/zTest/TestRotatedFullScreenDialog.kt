package com.example.mvvmframe.zTest

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.mvvmframe.R
import com.example.mvvmframe.databinding.DialogFullscreenTestBinding
import person.qaszxcwer.appbaseframe.dialog.BaseRotatedFullScreenDialogFragment
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/10
 * 
 */
class TestRotatedFullScreenDialog: BaseRotatedFullScreenDialogFragment<DialogFullscreenTestBinding>() {

    init {
        // 初始旋转方向
//        currentRotation = 1
    }

    override fun getViewBinding(inflater: LayoutInflater): DialogFullscreenTestBinding {
        isCancelable = false
        return DialogFullscreenTestBinding.inflate(inflater)
    }

    override fun initView() {
        binding.closeTV.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        binding.viewContent.setOnClickListener(View.OnClickListener {
            val newR: Int = when (currentRotation) {
                3 -> 0
                else -> currentRotation + 1
            }
            setRotation(newR)
        })
    }

    override fun initData() {
    }
}