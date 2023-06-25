# Activity
分为BaseActivity和BaseVMActivity，其中BaseVMActivity是BaseActivity的子类
## BaseActivity
不需要VM层的activity可以直接继承，泛型参数中填写layout文件生成的viewBinding的实现类，具体实现可以参考app/MainActivity

成员：
1. binding 是viewBinding的实现，类型取决于填写的泛型参数
2. mContext 即为activity自身

你需要按自己需求修改的方法：
1. openLogin() 这里是打开登录页面的，demo中使用AppJoint2跳转到了LoginActivity实现，根据你的需求进行修改
2. showLoading() 和 dismissLoading()，demo中显示和隐藏了AppBaseFrame/dialog/loading/UsualLoadingDialog，根据你的需求修改

多个可供重写的自定义方法：

在onCreate()中调用：
1. getViewBinding()，用于初始化binding，便于直接操作布局
2. doBeforeSetContentView()，按需重写，用于执行一些必须在setContentView()之前完成的操作
3. initParams()，按需重写，推荐在这里获取通过intent传入的参数
4. initView()，推荐在这里进行UI初始化，比如点击事件等
5. initData()，推荐在这里进行其他任务，比如网络请求中的返回结果监听、初始发起网络请求等

在onDestroy()中调用：
1. onReleaseResource()，按需重写，推荐在这里进行资源回收，比如一些只在这个页面使用的SDK解除初始化等

## BaseVMActivity
需要VM层的activity继承这个，泛型参数在父类的基础上多了一个VM，具体实现可以参考app/zTest/TestActivity

成员：
1. viewModel 是VM的具体实现，要求为BaseViewModel的子类

在获取到VM的类型之后，会进行VM的初始化，并且把之前提到的
1. showLoading()和dismissLoading()方法与VM层的showLoading进行关联
2. openLogin()与VM层的needLogin进行关联
3. 新增的 onVMError()与VM层的error进行关联

相比父类多了2个方法：
1. getViewModel()，返回VM的类型
2. onVMError()，按需重写，当VM层修改了error.value时会被调用