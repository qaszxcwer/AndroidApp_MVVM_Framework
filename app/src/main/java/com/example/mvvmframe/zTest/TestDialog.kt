package com.example.mvvmframe.zTest

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.mvvmframe.R
import person.qaszxcwer.appbaseframe.dialog.BaseDialogFragment

/**
 *
 * date: 2023/5/10
 * author: GuRongLin
 */
class TestDialog: BaseDialogFragment() {
    // 重写父类字段示例
    override val defaultGravity: Int
        get() = Gravity.BOTTOM
    override val cancelAble: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.dialog_test
    }

    override fun getAnimationStyle(): Int {
        return R.style.dialogBottomAnim;
    }

    private lateinit var closeTv: TextView

    override fun initView(view: View) {
        closeTv = view.findViewById(R.id.closeTV) as TextView
        closeTv.setOnClickListener(View.OnClickListener {
            dismiss()
        })
    }

    override fun initData() {
    }
}