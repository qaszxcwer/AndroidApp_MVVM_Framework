package person.qaszxcwer.appbaseframe.utils

import android.widget.Toast
import person.qaszxcwer.appbaseframe.BaseApplication

/**
 *
 * date: 2023/5/12
 * 
 * 记得不要在子线程中使用
 */
class ToastUtils {
    companion object {
        @JvmStatic
        fun showShort(charSequence: CharSequence) {
            showToast(charSequence, false)
        }

        @JvmStatic
        fun showLong(charSequence: CharSequence) {
            showToast(charSequence, true)
        }

        @JvmStatic
        private fun showToast(charSequence: CharSequence, longTime: Boolean) {
            val context = BaseApplication.application
            Toast.makeText(context, charSequence, when(longTime) {
                true -> Toast.LENGTH_LONG
                else -> Toast.LENGTH_SHORT
            }).show()
        }
    }
}