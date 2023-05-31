package person.qaszxcwer.appbaseframe.dialog.loading

import android.view.LayoutInflater
import person.qaszxcwer.appbaseframe.databinding.DialogUsualLoadingBinding
import person.qaszxcwer.appbaseframe.dialog.BaseDialogFragment

/**
 *
 * date: 2023/5/11
 * 
 */
class UsualLoadingDialog : BaseDialogFragment<DialogUsualLoadingBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): DialogUsualLoadingBinding {
        return DialogUsualLoadingBinding.inflate(inflater)
    }

    override fun initView() {
    }

    override fun initData() {
    }
}