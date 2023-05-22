package person.qaszxcwer.appbaseframe.extend

/**
 * 多变量判空
 */
fun <T> Any.notNull(vararg args: Any?, block: () -> T) =
    when (args.filterNotNull().size) {
    args.size -> block()
    else -> null
}