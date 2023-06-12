package com.example.mvvmframe.zTest

import android.content.Context
import android.content.Intent
import com.appjoint2.annotations.ServiceProvider
import com.example.appjoint2.ILoginAj
import person.qaszxcwer.appbaseframe.utils.ToastUtils

/**
 *
 * @author 顾荣林
 * 2023/6/12
 */
@ServiceProvider
class LoginAjImpl: ILoginAj {
    override fun gotoLogin(context: Context) {
        val intent = Intent(context, TestLoginActivity::class.java)
        context.startActivity(intent)
    }
}