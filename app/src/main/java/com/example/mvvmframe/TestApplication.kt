package com.example.mvvmframe

import com.appjoint2.annotations.AppSpec
import person.qaszxcwer.appbaseframe.BaseApplication
import person.qaszxcwer.appbaseframe.utils.ToastUtils

/**
 *
 * date: 2023/5/9
 * 使用AppSpec注解之后，需要在混淆文件中添加-keep规则，详见https://github.com/JustGank/AppJoint2/issues/1
 */
@AppSpec
class TestApplication: BaseApplication(){
    override fun onCreate() {
        super.onCreate()
        ToastUtils.showLong("TestApplication onCreate")
    }
}