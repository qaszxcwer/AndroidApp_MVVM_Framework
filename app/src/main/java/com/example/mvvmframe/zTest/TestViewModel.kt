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
            params["key"] = "换成你的"
            RetrofitClient.instance.usualApi.requestIp(params).enqueue(object: BaseNetCallback<IpBean>() {
                override fun onSuccess(data: IpBean) {
                    ipData.value = data
                }

                override fun onFail(t: Throwable): Boolean {
                    // 制造一个跳转登录的场景
                    needLogin.value = true
                    return false
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
            params["key"] = "换成你的"
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

    private val testSameVM by lazy {
        MutableLiveData<String>()
    }

    fun observeTestSameVM(owner: LifecycleOwner, observer: Observer<String>) {
        testSameVM.observe(owner, observer)
    }

    fun testSameViewModel(msg: String) {
        testSameVM.value = msg
    }
}