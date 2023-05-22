package person.qaszxcwer.appbaseframe.extend

/**
 *
 * date: 2023/5/17
 * author: GuRongLin
 * 这里放扩展函数
 */
class Extend {
}

/**
 * 多变量判空
 */
fun <T> Any.notNull(vararg args: Any?, block: () -> T) =
    when (args.filterNotNull().size) {
    args.size -> block()
    else -> null
}