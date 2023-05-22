package person.qaszxcwer.appbaseframe.dialog

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.DecelerateInterpolator
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import person.qaszxcwer.appbaseframe.R
import person.qaszxcwer.appbaseframe.utils.DeviceUtils

/**
 *
 * date: 2023/5/9
 * author: GuRongLin
 * 可以旋转的全屏对话框样式，然后如果不需要全屏就在xml里面控制自己高度即可
 */
abstract class BaseRotatedFullScreenDialogFragment: BaseDialogFragment() {
    final override val defaultGravity: Int
        get() = Gravity.CENTER

    override val cancelAble: Boolean
        get() = false

    init {
        setStyle(STYLE_NORMAL, R.style.fullScreenDialog)
    }
    /**
     * 旋转的方向,只能为以下四个值之一:<BR>
     * 从上到下分别是0,1,2,3<BR>
     * android.view.Surface#ROTATION_0<BR>
     * android.view.Surface#ROTATION_90<BR>
     * android.view.Surface#ROTATION_180<BR>
     * android.view.Surface#ROTATION_270<BR>
     */
    protected var currentRotation: Int = 0

    private var firstCreateDialog: Boolean = true

    private lateinit var rootView:View

    private var transOffset:Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootView = view
        super.onViewCreated(view, savedInstanceState)
    }

    override fun changeDialogWindowAttrs() {
        val window = dialog?.window
        window?.let {
            window.decorView.setPadding(0, 0, 0, 0)
            window.decorView.setBackgroundColor(Color.TRANSPARENT)

            // 从androidx.core:core-ktx 1.5.0开始可用
            val controller = WindowCompat.getInsetsController(window, rootView)
            // 自动隐藏状态栏
            controller?.hide(WindowInsetsCompat.Type.statusBars())
        }
    }

    override fun onResume() {
        super.onResume()
        setRotation(currentRotation)
    }

    fun setRotation(rotation: Int) {
        if ((currentRotation == rotation && !firstCreateDialog)
            || rotation < 0 || rotation > 3) {
                // 非首次旋转且旋转方向和上次一致的情况、参数超出范围的情况
            return
        }
        currentRotation = rotation
        if (dialog?.window == null) {
            return
        }
        val window = dialog!!.window!!
        onWindowDisplayPosition(window, rotation)
        val size: Array<Int> = getFilledViewSize(rotation)
        setViewSize(size[0], size[1])
        rotateRootView(rootView, rotation)
    }

    /**
     * 设置窗口的显示位置和大小.
     */
    private fun onWindowDisplayPosition(window: Window, rotation: Int) {
        // 设置弹窗显示位置
        window.setGravity(Gravity.CENTER)
        // 设置窗口大小
        val windowSize = DeviceUtils.getScreenSize(context)
        var w: Int
        var h: Int
        if (rotation == 1 || rotation == 3) { // 横屏状态
            w = windowSize[1]
            h = windowSize[0]
            if (w < h) {
                // 横屏状态宽度必然要大于高度，本来是不会错的，遇到动态切换屏幕方向的场景就可能需要调换位置了
                w = windowSize[0]
                h = windowSize[1]
            }
        } else {
            w = windowSize[0]
            h = windowSize[1]
        }

        changeDialogWindowAttrs()
        window.setLayout(w, h)
    }

    /**
     * 获取旋转后充满[.mRootView]的Size大小
     * 分两种情况:
     * 竖屏时,使用当前的window宽高
     * 横屏时,交换当前window的宽高作为字view的旋转后的大小
     */
    private fun getFilledViewSize(rotation: Int): Array<Int> {
        val attributes = dialog!!.window!!.attributes
        val width: Int
        val height: Int
        if (rotation == 1 || rotation == 3) {
            width = attributes.height
            height = attributes.width
        } else {
            width = attributes.width
            height = attributes.height
        }
        val size = arrayOf(0, 0)
        size[0] = width
        size[1] = height
        return size
    }

    /**
     * 重新设置子View的大小，使布局充满整个window的size
     */
    private fun setViewSize(width: Int, height: Int) {
        rootView.layoutParams.width = width
        rootView.layoutParams.height = height
        rootView.layoutParams = rootView.layoutParams

        //重新根据rootView的大小，设置旋转后需要的偏移量
        transOffset = width / 2 - height / 2
    }

    /**
     * 旋转View到正确的位置
     */
    private fun rotateRootView(rootView: View, rotation: Int) {
        val degree = 90 * rotation
        val transX: Int
        val transY: Int
        if (rotation == 1 || rotation == 3) { //横屏
            transX = -transOffset
            transY = transOffset
        } else { //竖屏
            transX = 0
            transY = 0
        }
        val duration = if (firstCreateDialog) 0 else 200
        rootView.animate()
            .rotation(degree.toFloat())
            .translationX(transX.toFloat())
            .translationY(transY.toFloat())
            .setInterpolator(DecelerateInterpolator())
            .setDuration(duration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    firstCreateDialog = false
                }
            })
    }
}