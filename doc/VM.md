# VM
在本框架中为BaseViewModel，有一个launchVMScope()方法供子类使用

这个方法是借助VM的协程和生命周期，在协程中运行一个代码块，当VM的生命周期终止时，代码块也会提前结束

如果在代码块中进行网络请求和接口返回的监听，代码块提前结束后就不会再收到接口返回了，避免操作已经销毁的activity导致的问题

## 其他成员
均用于和BaseVMActivity交互
1. showLoading和observeShowDialog()用于在activity中展示loading
2. needLogin和observeLogin()用于通知activity跳转登录页
3. error和observeError()用于调用activity的onVMError()方法