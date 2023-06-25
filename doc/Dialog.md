# Dialog
主要为BaseDialogFragment和BaseRotatedFullScreenDialogFragment
## BaseDialogFragment
对话框基础类，继承的DialogFragment而非Dialog，这样可以适应旋转屏幕的需求

自带了style以实现水平方向充满屏幕，高度自适应，默认垂直居中，如有需要可以在子类中重写父类方法和参数实现自定义的样式

具体实现可以参考app/zTest/TestDialog和APPBaseFrame/.../dialog/loading/UsualLoadingDialog

类比的[activity](https://github.com/qaszxcwer/AndroidApp_MVVM_Framework/blob/main/doc/Activity.md)，一样具有initView()和initData()方法，在onViewCreated()方法中调用

提供了show()方法用于显示对话框，在方法内部判断了是否已经显示和是否不需要显示，是我在工作中曾经碰到的crash原因，同时固定用类名作为showTag，因为我还没见过需要使用showTag才能实现的需求

可供重写的方法：
1. changeDialogWindowAttrs() 内部实现了水平充满、高度自适应，默认居中，入场退场动画
2. getViewBinding() 负责提供viewBinding的，类型取决于泛型
3. getAnimationStyle() 按需重写，负责提供入场退场动画
4. initView() 建议在这里初始化UI
5. initData() 建议在这里做其他事情

## BaseRotatedFullScreenDialogFragment
是BaseDialogFragment的子类 ，是可以旋转的全屏对话框，过去某个需求需要对话框旋转，然后照着[这篇文章](https://www.jianshu.com/p/b986df7951a4)实现了，感觉以后可能会遇到

使用setRotation()方法来修改对话框的旋转方向

## loading
里面有UsualLoadingDialog和用于控制显示的util

util内部手动实现了个懒加载的单例对象，用于控制loading对话框的显示隐藏(当时完全没意识到object关键字可以直接实现单例)

UsualLoadingDialog是一个普通的对话框，是BaseDialogFragment的子类