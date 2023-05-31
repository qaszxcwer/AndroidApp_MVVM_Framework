package com.example.mvvmframe.zTest

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mvvmframe.api.BaseListResponse
import com.example.mvvmframe.api.BaseNetCallback
import com.example.mvvmframe.api.RetrofitClient
import com.example.mvvmframe.zTestBean.IpBean
import com.example.mvvmframe.zTestBean.NameItem
import person.qaszxcwer.appbaseframe.vm.BaseViewModel
import kotlin.collections.HashMap
import kotlin.collections.set

/**
 *
 * date: 2023/5/6
 * 
 */
class TestViewModel: BaseViewModel() {
    companion object {
        private const val LogTAG = "TestVM_LogTag"
    }

    private val ipData by lazy {
        MutableLiveData<IpBean>()
    }

    fun observeIpData(owner: LifecycleOwner, observer: Observer<IpBean>) {
        ipData.observe(owner, observer)
    }

    fun getIp() {
        showLoading.value = true
        launchVMScope {
            val params: HashMap<String, Any> = HashMap()
            params["ip"] = "112.112.11.11"
            RetrofitClient.instance.usualApi.requestIp(params).enqueue(object: BaseNetCallback<IpBean>() {
                override fun onSuccess(data: IpBean) {
                    ipData.value = data
                }
            })
        }
    }

    private val nameList by lazy {
        MutableLiveData<BaseListResponse<NameItem>>()
    }

    fun observeNameList(owner: LifecycleOwner, observer: Observer<BaseListResponse<NameItem>>) {
        nameList.observe(owner, observer)
    }

    fun getNameList() {
        showLoading.value = true
        launchVMScope {
            val params: HashMap<String, Any> = HashMap()
            params["surname"] = "公孙"
            RetrofitClient.instance.usualApi.requestNameList("query", params).enqueue(object: BaseNetCallback<BaseListResponse<NameItem>>() {
                override fun onSuccess(data: BaseListResponse<NameItem>) {
                    nameList.value = data
                }

                override fun needLogin() {
                    needLogin.value = true
                }

                override fun onFail(t: Throwable): Boolean {
                    error.value = t
                    return false
                }
            })
        }
    }
}