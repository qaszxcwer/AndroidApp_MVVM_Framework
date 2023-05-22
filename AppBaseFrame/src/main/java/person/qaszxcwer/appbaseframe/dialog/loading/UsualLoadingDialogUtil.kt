package person.qaszxcwer.appbaseframe.dialog.loading

import androidx.fragment.app.FragmentManager

/**
 *
 * date: 2023/5/11
 * author: GuRongLin
 */
class UsualLoadingDialogUtil {
    companion object {
        val instance by lazy { UsualLoadingDialogUtil() }
    }

    private var loadingDialog: UsualLoadingDialog? = null

    @Synchronized
    fun show(fragmentManager: FragmentManager) {
        dismiss()
        loadingDialog = UsualLoadingDialog()
        loadingDialog?.show(fragmentManager)
    }

    @Synchronized
    fun dismiss() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}