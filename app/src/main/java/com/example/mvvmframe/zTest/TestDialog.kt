package com.example.mvvmframe.zTest

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.mvvmframe.R
import com.example.mvvmframe.databinding.DialogTestBinding
import person.qaszxcwer.appbaseframe.dialog.BaseDialogFragment
import person.qaszxcwer.appbaseframe.utils.LogUtils

/**
 *
 * date: 2023/5/10
 * 
 */
class TestDialog: BaseDialogFragment<DialogTestBinding>() {
    // 重写父类字段示例
    override val defaultGravity: Int
        get() = Gravity.BOTTOM

    override fun getViewBinding(inflater: LayoutInflater): DialogTestBinding {
        isCancelable = true
        return DialogTestBinding.inflate(inflater)
    }

    override fun getAnimationStyle(): Int {
        return R.style.dialogBottomAnim;
    }

    override fun initView() {
        binding.closeTV.setOnClickListener(View.OnClickListener {
            dismiss()
        })
    }

    override fun initData() {
    }
}