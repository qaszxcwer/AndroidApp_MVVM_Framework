package person.qaszxcwer.appbaseframe.utils

import android.util.Log
import person.qaszxcwer.appbaseframe.BuildConfig

/**
 *
 * date: 2023/5/9
 * 
 */
class LogUtils {
    companion object {
        private const val DefaultLogTag = "LogUtilsDefaultTag"

        @JvmStatic
        fun v(logMessage: String, logTag: String = DefaultLogTag) {
            if (!BuildConfig.DEBUG) {
                return
            }
            Log.v(logTag, logMessage)
        }

        @JvmStatic
        fun d(logMessage: String, logTag: String = DefaultLogTag) {
            if (!BuildConfig.DEBUG) {
                return
            }
            Log.d(logTag, logMessage)
        }

        @JvmStatic
        fun i(logMessage: String, logTag: String = DefaultLogTag) {
            if (!BuildConfig.DEBUG) {
                return
            }
            Log.i(logTag, logMessage)
        }

        @JvmStatic
        fun w(logMessage: String, logTag: String = DefaultLogTag) {
            if (!BuildConfig.DEBUG) {
                return
            }
            Log.w(logTag, logMessage)
        }

        @JvmStatic
        fun e(logMessage: String, logTag: String = DefaultLogTag) {
            if (!BuildConfig.DEBUG) {
                return
            }
            Log.e(logTag, logMessage)
        }
    }
}