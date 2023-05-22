package person.qaszxcwer.appbaseframe

import android.app.Application

/**
 *
 * date: 2023/5/6
 * author: GuRongLin
 */
open class BaseApplication: Application() {
    companion object {
        lateinit var application: BaseApplication
    }

    override fun onCreate() {
        application = this
        super.onCreate()
    }
}