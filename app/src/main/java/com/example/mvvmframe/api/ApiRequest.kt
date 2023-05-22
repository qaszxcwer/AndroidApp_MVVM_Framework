package com.example.mvvmframe.api

import com.example.mvvmframe.zTest.IpBean
import com.example.mvvmframe.zTest.NameItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 *
 * date: 2023/3/28
 * author: GuRongLin
 */
interface ApiRequest {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("ip/ipNewV3")
    fun requestIp(@Body requestBody: Any): Call<BaseResponse<IpBean>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("fapigw/naming/{key1}")
    // 列表接口，同时也是往接口地址路径里面放参数的示例
    fun requestNameList(@Path("key1") key1: String, @Body requestBody: Any): Call<BaseResponse<BaseListResponse<NameItem>>>
}