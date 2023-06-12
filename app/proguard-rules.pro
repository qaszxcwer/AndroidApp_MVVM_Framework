# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 网络请求的数据bean不能混淆，否则json转化会出错
-keep class com.example.mvvmframe.api.BaseResponse {*;}
-keep class com.example.mvvmframe.api.BaseListResponse {*;}
-keep class com.example.mvvmframe.zTestBean.** {*;}

# 和H5交互的方法不能被混淆
-keepclassmembers class com.example.mvvmframe.zTest.TestJsInterface {
   public *;
}

# AppJoint2的接口实现
-keep class com.example.mvvmframe.zTest.LoginAjImpl {*;}