package com.example.mvvmframe.zTest

import android.content.Intent
import com.example.mvvmframe.databinding.ActivityMainBinding
import person.qaszxcwer.appbaseframe.activity.BaseActivity

/**
 *
 * date: 2023/5/26
 * 
 */
class TestResultActivity: BaseActivity<ActivityMainBinding>() {
    companion object {
        const val KEY_Result = "KEY_Result"
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
    }

    override fun initData() {
        val resultIntent = Intent()
        resultIntent.putExtra(KEY_Result, "abcdefg")
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}