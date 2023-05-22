package person.qaszxcwer.appbaseframe.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import person.qaszxcwer.appbaseframe.vm.BaseViewModel

/**
 *
 * date: 2023/5/12
 * author: GuRongLin
 */
abstract class BaseVMFragment<VM: BaseViewModel, T: ViewBinding>: BaseFragment<T>() {
    protected lateinit var viewModel: VM

    protected abstract fun getViewModel(): Class<VM>

    override fun doAfterViewBinding() {
        initVM()
    }

    private fun initVM() {
        getViewModel().let {
            viewModel = ViewModelProvider(this)[it]
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