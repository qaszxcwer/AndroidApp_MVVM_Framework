package person.qaszxcwer.appbaseframe.net

/**
 *
 * date: 2023/5/9
 * author: GuRongLin
 * 自定义的网络请求Exception
 */
class ApiException(errorCode: Int, errorMessage: String):RuntimeException(errorMessage) {
    var errorCode: Int = errorCode

    companion object {
        @JvmStatic
        fun getTypeErrorApiException(): ApiException {
            return ApiException(10000, "接口返回的字段不是BaseResponse类型")
        }
    }
}