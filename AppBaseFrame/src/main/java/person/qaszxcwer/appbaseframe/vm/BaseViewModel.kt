package person.qaszxcwer.appbaseframe.vm

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import person.qaszxcwer.appbaseframe.net.SingleLiveData

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
open class BaseViewModel: ViewModel(), LifecycleObserver {
    //运行在UI线程的协程，包一层的目的是借助VM的生命周期控制接口提前取消
    fun launchVMScope(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        block()
    }

    protected val showLoading = MutableLiveData<Boolean>()

    fun observeShowDialog(owner: LifecycleOwner, observer: Observer<Boolean>) {
        showLoading.observe(owner, observer)
    }

    protected val needLogin = SingleLiveData<Boolean>()

    fun observeLogin(owner: LifecycleOwner, observer: Observer<Boolean?>) {
        needLogin.observe(owner, observer)
    }

    protected val error = SingleLiveData<Throwable>()

    fun observeError(owner: LifecycleOwner, observer: Observer<Throwable?>) {
        error.observe(owner, observer)
    }
}