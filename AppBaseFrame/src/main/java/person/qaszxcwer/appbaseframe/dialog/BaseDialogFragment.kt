package person.qaszxcwer.appbaseframe.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import person.qaszxcwer.appbaseframe.R

/**
 *
 * date: 2023/5/9
 * author: GuRongLin
 * 普通的对话框，左右充满，高度适应内容，上下默认居中<BR>
 * 实现getLayoutId方法即可创建UI
 */
abstract class BaseDialogFragment: DialogFragment() {
    open val defaultGravity: Int = Gravity.CENTER
    open val cancelAble:Boolean = true

    init {
        setStyle(STYLE_NORMAL, R.style.normalDialog)
        isCancelable = cancelAble
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeDialogWindowAttrs()
        initView(view)
        initData()
    }

    internal open fun changeDialogWindowAttrs() {
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

    abstract fun getLayoutId(): Int

    protected open fun getAnimationStyle(): Int {
        return 0
    }

    abstract fun initView(view: View)

    abstract fun initData()
}