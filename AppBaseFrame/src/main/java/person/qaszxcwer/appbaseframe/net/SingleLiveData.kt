package person.qaszxcwer.appbaseframe.net

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 *
 * date: 2023/5/12
 * 
 * 借助原子操作实现多线程(主要指网络请求)工作时的稳定性
 */
class SingleLiveData<T> : MutableLiveData<T?>() {
    private val mPending = AtomicBoolean(false)
    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        super.observe(owner, { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    /**
     * 重写setValue就够了
     * postValue最终会在主线程调用setValue，所以无需重写
     */
    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }
}