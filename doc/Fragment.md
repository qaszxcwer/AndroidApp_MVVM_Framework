# Fragment
和[activity](https://github.com/qaszxcwer/AndroidApp_MVVM_Framework/blob/main/doc/Activity.md)一样，分成BaseFragment和BaseVMFragment，分别对应不带和带VM层的，同样的泛型形式

各种函数也几乎一致，由于fragment生命周期与activity不同，产生区别如下：
1. initParams()在onCreate()中调用
2. doAfterViewBinding()对应activity中的doBeforeSetContentView()，在onCreateView()中调用
3. initView()和initData()在onViewCreated()中调用