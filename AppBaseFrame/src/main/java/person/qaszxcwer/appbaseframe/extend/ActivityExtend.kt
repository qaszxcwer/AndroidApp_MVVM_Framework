package person.qaszxcwer.appbaseframe.extend

import android.graphics.Color
import android.os.Build
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat.Type
import person.qaszxcwer.appbaseframe.utils.DeviceUtils

/**
 * Immerse 沉浸。设置沉浸的方式
 *
 * @param type 取值范围Type.systemBars(),Type.statusBars(),Type.navigationBars()
 * @param statusBarBlack 状态栏文字 true 黑色,false 白色，仅沉浸导航栏时不生效
 * @param navigationIsBlack 导航栏按钮 true 黑色,false 白色，仅沉浸状态栏时不生效
 * @param color 状态或/和导航栏的背景颜色，是或还是和，根据type决定
 */
fun AppCompatActivity.immerse(
    @Type.InsetsType type: Int = Type.systemBars(),
    statusBarBlack: Boolean = true,
    navigationIsBlack: Boolean = true,
    @ColorInt color: Int = Color.TRANSPARENT
) {
    when (type) {
        Type.systemBars() -> { //沉浸入导航栏和状态栏
            WindowCompat.setDecorFitsSystemWindows(window, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = color
                window.navigationBarColor = color
            }
            ViewCompat.getWindowInsetsController(window.decorView)?.let { controller ->
                controller.isAppearanceLightStatusBars = statusBarBlack
                controller.isAppearanceLightNavigationBars = navigationIsBlack
            }
            findViewById<FrameLayout>(android.R.id.content).apply {
                setPadding(0, 0, 0, 0)
            }
        }
        Type.statusBars() -> {//只沉浸入状态栏
            WindowCompat.setDecorFitsSystemWindows(window, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = color
            }
            ViewCompat.getWindowInsetsController(window.decorView)?.isAppearanceLightStatusBars = statusBarBlack
            findViewById<FrameLayout>(android.R.id.content).apply {
                post {
                    setPadding(0, 0, 0, DeviceUtils.getNavigationBarHeight(this@immerse))
                }
            }
        }
        Type.navigationBars() -> {//只沉浸入导航栏
            WindowCompat.setDecorFitsSystemWindows(window, false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.navigationBarColor = color
            }
            ViewCompat.getWindowInsetsController(window.decorView)?.isAppearanceLightNavigationBars = navigationIsBlack
            findViewById<FrameLayout>(android.R.id.content).apply {
                post {
                    setPadding(0, DeviceUtils.getStatusBarHeight(this@immerse), 0, 0)
                }
            }
        }
        else -> Unit
    }
}