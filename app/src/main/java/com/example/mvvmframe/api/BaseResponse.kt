package com.example.mvvmframe.api

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
// todo 根据自己的业务逻辑，把公共字段写在这里，比如注释里面这样的
//data class BaseResponse<T>(
//    val code: Int,
//    val message: String,
//    val data: T,
//)

data class BaseResponse<T>(
    var error_code:Int,
    val reason:String,
    val result:T,
    val resultcode:String,
)