package person.qaszxcwer.appbaseframe.fragment

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import person.qaszxcwer.appbaseframe.vm.BaseViewModel

/**
 *
 * date: 2023/5/12
 * 
 */
abstract class BaseVMFragment<VM: BaseViewModel, T: ViewBinding>: BaseFragment<T>() {
    protected lateinit var viewModel: VM

    protected abstract fun getViewModel(): Class<VM>

    override fun doAfterViewBinding() {
        initVM()
    }

    /**
     * 当需要fragment和activity共用同一个viewModel时，重写此方法，返回activity
     */
    protected open fun getVmOwnerActivity() : FragmentActivity? {
        return null
    }

    private fun initVM() {
        getViewModel().let {
            getVmOwnerActivity()?.let { act ->
                viewModel = ViewModelProvider(act)[it]
            }
            if (!this::viewModel.isInitialized) {
                viewModel = ViewModelProvider(this)[it]
            }
            viewModel.observeShowDialog(this, { bool ->
                if (bool != null && bool) {
                    showLoading()
                } else {
                    dismissLoading()
                }
            })
            viewModel.observeLogin(this, { bool ->
                if (bool != null && bool) {
                    openLogin()
                }
            })
            viewModel.observeError(this, { t ->
                if (t != null) {
                    onVMError(t)
                }
            })
        }
    }

    protected open fun onVMError(t: Throwable) {}
}