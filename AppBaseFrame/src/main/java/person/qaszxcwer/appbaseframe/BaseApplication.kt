package person.qaszxcwer.appbaseframe

import android.app.Application
import com.appjoint2.annotations.ModuleSpec

/**
 *
 * date: 2023/5/6
 * 
 */
@ModuleSpec(priority = 1)
// todo 为什么作为父类也要加注解？否则生命周期不执行？等待AppJoint2作者解答
open class BaseApplication: Application() {
    companion object {
        lateinit var application: BaseApplication
    }

    override fun onCreate() {
        application = this
        super.onCreate()
    }
}