package person.qaszxcwer.appbaseframe.utils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 *
 * date: 2023/5/10
 * 
 */
class DeviceUtils {
    companion object {
        /**
         * 获取屏幕宽高，会去除状态栏、导航栏等区域的宽高
         *
         * @return 0宽度、1高度
         */
        @JvmStatic
        fun getScreenSize(context: Context?): IntArray {
            val size = IntArray(2)
            context?.let {
                size[0] = it.resources.displayMetrics.widthPixels
                size[1] = it.resources.displayMetrics.heightPixels
            }
            return size
        }

        /**
         * 获得真实的屏幕宽度和高度
         *
         * @return 0宽度、1高度
         */
        @JvmStatic
        fun getScreenSizeReal(context: Context?): IntArray {
            val size = IntArray(2)
            context?.let {
                val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                        val bounds = wm.currentWindowMetrics.bounds
                        size[0] = bounds.width()
                        size[1] = bounds.height()
                    }
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
                        val outMetrics = DisplayMetrics()
                        wm.defaultDisplay.getRealMetrics(outMetrics)
                        size[0] = outMetrics.widthPixels
                        size[1] = outMetrics.heightPixels
                    }
                    else -> {
                        // Android 4.1没有这些api，只能用不带状态栏虚拟导航栏的尺寸应付一下
                        return getScreenSize(context)
                    }
                }
            }
            return size
        }

        /**
         * 获得状态栏高度
         */
        @JvmStatic
        fun getStatusBarHeight(context: Context): Int {
            var statusBarHeight = 0
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
            return statusBarHeight
        }

        /**
         * 获得导航栏高度
         */
        @JvmStatic
        fun getNavigationBarHeight(context: Context): Int {
            var result = 0
            val resourceId =
                context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }
    }
}