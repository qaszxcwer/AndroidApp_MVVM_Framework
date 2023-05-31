package person.qaszxcwer.appbaseframe.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import person.qaszxcwer.appbaseframe.R

/**
 *
 * date: 2023/5/9
 * 
 * 普通的对话框，左右充满，高度适应内容，上下默认居中<BR>
 * 实现getLayoutId方法即可创建UI
 */
abstract class BaseDialogFragment<T: ViewBinding>: DialogFragment() {
    protected open val defaultGravity: Int = Gravity.CENTER

    init {
        setStyle(STYLE_NORMAL, R.style.normalDialog)
    }

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeDialogWindowAttrs()
        initView()
        initData()
    }

    protected open fun changeDialogWindowAttrs() {
        val window = dialog?.window
        window?.let {
            when(val animation = getAnimationStyle()) {
                0 -> Unit
                else -> window.setWindowAnimations(animation)
            }
            // 即使在style中设置了全屏，鸿蒙系统也需要这个才能全屏
            window.decorView.setPadding(0, 0, 0, 0)
            window.decorView.setBackgroundColor(Color.TRANSPARENT)
            val params = window.attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            if (defaultGravity != 0) {
                window.setGravity(defaultGravity)
            }
            window.attributes = params
        }
    }

    fun show(manager: FragmentManager) {
        if (isAdded) {
            // 正在显示的不重复显示，否则会闪退
            return
        }
        if (manager.isDestroyed) {
            return
        }
        super.show(manager, javaClass.simpleName)
    }

    protected abstract fun getViewBinding(inflater: LayoutInflater): T

    protected open fun getAnimationStyle(): Int {
        return 0
    }

    protected abstract fun initView()

    protected abstract fun initData()
}