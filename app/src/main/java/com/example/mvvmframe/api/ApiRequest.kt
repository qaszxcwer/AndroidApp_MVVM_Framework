package com.example.mvvmframe.api

import com.example.mvvmframe.zTestBean.IpBean
import com.example.mvvmframe.zTestBean.NameItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 *
 * date: 2023/3/28
 * todo 等待retrofit2适配AGP8.0，然后从框架Lib的consumer-rules文件中删除关于retrofit的混淆策略
 */
interface ApiRequest {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("ip/ipNewV3")
    fun requestIp(@QueryMap requestBody: HashMap<String, Any>): Call<BaseResponse<IpBean>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("fapigw/naming/{key1}")
    // 列表接口，同时也是往接口地址路径里面放参数的示例
    fun requestNameList(@Path("key1") key1: String, @QueryMap requestBody: HashMap<String, Any>): Call<BaseResponse<BaseListResponse<NameItem>>>
}