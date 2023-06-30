# Utils 工具类

## DeviceUtils
存放了一堆和设备有关的方法，详见方法注释

## LogUtils
一开始是准备用这个统一控制在release状态下不打印日志的，后面换成在混淆规则中配置了去除日志打印之后就没用了，准备废弃了

## ToastUtils
可以只传递要toast的文本就直接显示的工具，作用是省略了传递context和时间长短这2个参数，还有show()方法的调用，减少重复代码

由于高版本的targetSDK 已经不支持自定义toast样式，当存在自定义样式的需求时，建议使用snackbar或者引入其他toast库