package person.qaszxcwer.appbaseframe.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.appjoint2.core.AppJoint2
import com.example.appjoint2.ILoginAj
import person.qaszxcwer.appbaseframe.dialog.loading.UsualLoadingDialogUtil
import person.qaszxcwer.appbaseframe.utils.ToastUtils

/**
 *
 * date: 2023/5/12
 * 
 */
abstract class BaseFragment<T: ViewBinding>: Fragment() {
    protected lateinit var mContext: Context
    protected lateinit var binding: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { initParams(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater)
        doAfterViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseResource()
    }

    protected open fun doAfterViewBinding() {}

    protected abstract fun getViewBinding(inflater: LayoutInflater):T

    protected open fun initParams(bundle: Bundle) {}

    protected abstract fun initView()

    protected abstract fun initData()

    protected fun showLoading() {
        activity?.let { UsualLoadingDialogUtil.instance.show(it.supportFragmentManager) }
    }

    protected fun dismissLoading() {
        UsualLoadingDialogUtil.instance.dismiss()
    }

    protected fun openLogin() {
        val loginAj = AppJoint2.service(ILoginAj::class.java)
        loginAj?.gotoLogin(mContext)
    }

    private fun releaseResource() {
        dismissLoading()
        onReleaseResource()
    }

    protected open fun onReleaseResource() {}
}