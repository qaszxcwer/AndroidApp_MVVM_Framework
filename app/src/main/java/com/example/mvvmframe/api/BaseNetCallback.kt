package com.example.mvvmframe.api

import person.qaszxcwer.appbaseframe.dialog.loading.UsualLoadingDialogUtil
import person.qaszxcwer.appbaseframe.net.ApiException
import person.qaszxcwer.appbaseframe.utils.LogUtils
import person.qaszxcwer.appbaseframe.utils.ToastUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

/**
 *
 * date: 2023/5/9
 * author: GuRongLin
 * 统一处理接口请求的出错情况，可根据自己的业务逻辑修改
 */
abstract class BaseNetCallback<T>: Callback<BaseResponse<T>> {
    final override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        UsualLoadingDialogUtil.instance.dismiss()
        if (response.body() !is BaseResponse<*>) {
            onFailure(call, ApiException.getTypeErrorApiException())
            return
        }
        val baseResponse: BaseResponse<T> = response.body() as BaseResponse<T>
        if (baseResponse.error_code > 0) {
            onFailure(call, ApiException(baseResponse.error_code, baseResponse.reason))
            return
        }
        onSuccess(baseResponse.result) // todo 检查回调是否成功
    }

    final override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
        UsualLoadingDialogUtil.instance.dismiss()
        LogUtils.e("接口请求出错, ${t.javaClass.simpleName}:${t.localizedMessage}")
        if (onFail(t)) {
            return
        }
        dealWithFailure(t)
    }

    /**
     * 默认的处理流程
     */
    private fun dealWithFailure(t: Throwable) {
        if (t is ApiException) {
            val apiException = t as ApiException
            when (apiException.errorCode) {
                // 自己的登录过期、需要登录编码
                10086 -> needLogin()
                else ->  ToastUtils.showShort("" + apiException.localizedMessage)
            }
            return
        }
        if (t is HttpException) {
            ToastUtils.showShort(t.message())
            return
        }
        ToastUtils.showShort("网络连接失败，请稍后再试")
    }

    /**
     * 考虑到并非所有的接口都必须登录后才能调用，所以需要检查登录状况的接口重写此方法，进行登录
     */
    protected open fun needLogin() {}

    /**
     * 自定义的出错处理，如有需要进行重写
     * @return true表示处理完成，不需要走默认处理的流程；false表示需要走默认处理流程
     */
    protected open fun onFail(t: Throwable): Boolean {
        return false
    }

    abstract fun onSuccess(data: T)
}