package person.qaszxcwer.appbaseframe.activity

import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import person.qaszxcwer.appbaseframe.vm.BaseViewModel

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
abstract class BaseVMActivity<T: ViewBinding, VM: BaseViewModel> : BaseActivity<T>() {
    protected lateinit var viewModel: VM

    override fun doBeforeSetContentView() {
        initVM()
    }

    protected abstract fun getViewModel(): Class<VM>

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