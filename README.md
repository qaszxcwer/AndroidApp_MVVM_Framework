# 简介
一个基于Kotlin+MVVM+Retrofit的原生APP开发框架

使用viewBinding，demo中已经包含了多渠道（区分服务器地址和签名密钥文件）和混淆设置

目前使用的第三方开源库为：
1. [Retrofit2](https://github.com/square/retrofit) (网络请求封装)
2. OkHttp3 (由Retroft2连带引用) (网络请求)
3. Gson (由Retroft2连带引用)  (Json转化)
4. [Glide](https://github.com/bumptech/glide) (图片加载)
5. [AppJoint2](https://github.com/JustGank/AppJoint2) (模块化)
6. [LiveEventBus](https://github.com/JeremyLiao/LiveEventBus) (低代码实现应用内消息通知)

计划使用的第三方库为：
1. [BRVAH4](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) (快速实现recyclerview)(在等正式版，当前为alpha阶段)

# 快速使用
## 运行demo
1. Android Studio Flamingo要求使用AGP8和JDK17，本工程为了在这个版本的AS上运行，已经升级到了gradle 8.0.2，如果你的AS运行存在问题，可能需要升级AS（推荐，升级之后混淆速度快了很多）或者项目的gradle降级到7.0.0
2. 在app module下创建签名使用的密钥文件，密钥的文件名、别名、密码等需要与app/build.gradle中的配置保持一致

## 在项目中使用
1. AppBaseFrame和AppJoint2这2个module是框架内容，需要import到你的项目中，app下的内容是演示demo无需import
2. 仿照app/包名/api下的所有文件，编写适应你项目需求的代码以实现网络请求和接口返回的统一处理，特别注意修改标注了todo的部分

# 框架介绍
## 简介
点击超链接查看各个部分的详细文档 todo……
### V层 UI部分
分为：
1. Activity
2. Fragment
3. Dialog
4. View
### VM层 交互桥梁
分为：
1. VM
2. hybrid
### M层 数据部分
分为：
1. net
### 其他
分为：
1. extend扩展方法
2. utils工具类
3. BaseApplication
## Activity
分为BaseActivity和BaseVMActivity，其中BaseVMActivity是BaseActivity的子类
### BaseActivity
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

### BaseVMActivity
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

