package com.example.mvvmframe.api

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
// todo 根据自己的业务逻辑，把列表接口的字段写在这里
data class BaseListResponse<T>(
    val data: ArrayList<T>,
    val page: Int,
    val pageSize: Int,
)