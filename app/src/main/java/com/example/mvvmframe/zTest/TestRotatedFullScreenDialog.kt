package com.example.mvvmframe.zTest

import android.view.View
import android.widget.TextView
import com.example.mvvmframe.R
import person.qaszxcwer.appbaseframe.dialog.BaseRotatedFullScreenDialogFragment

/**
 *
 * date: 2023/5/10
 * author: GuRongLin
 */
class TestRotatedFullScreenDialog: BaseRotatedFullScreenDialogFragment() {
    private lateinit var closeTv: TextView
    private lateinit var viewTitle : TextView
    private lateinit var viewContent : TextView

    init {
        // 初始旋转方向
//        currentRotation = 1
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_fullscreen_test
    }

    override fun initView(view: View) {
        closeTv = view.findViewById(R.id.closeTV) as TextView
        closeTv.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        viewContent = view.findViewById(R.id.viewContent) as TextView
        viewContent.setOnClickListener(View.OnClickListener {
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