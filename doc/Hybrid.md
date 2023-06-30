# Hybrid
用于APP和H5进行交互，分为2个部分：事件管理、事件接口

整体的工作流程如下：
1. webview使用addJavascriptInterface()方法，添加了一堆方法供H5页面调用
2. H5调用了其中一个方法，比如说是app/zTest/TestJsInterface中的jsEvent()，数据可以转化为HybridEvent
3. HybridEventHandlerManager中取出operationType可以匹配的handler，交由handler进行处理
4. handler处理中，可能会拉起另一个activity（需要实现IHybrid），可能会收到activity的返回值（需要handler实现IActivityResultHandler）
5. handler处理完成，可能会需要使用NativeEventHandler告知H5

## 事件管理
涉及到：
1. BaseHybridEventHandler
2. HybridEvent
3. HybridEventHandlerManager
4. NativeEventHandler

1、2、3是处理H5向APP发来的数据，4是APP向H5发送数据

### BaseHybridEventHandler
处理H5发来事件的基类，有2个方法需要子类实现，可以参考H5EventHandler_A和H5EventHandler_B
1. getOperationType() 返回H5事件的匹配字段
2. onHandleEvent() 处理匹配到的H5事件

在preHandleEvent()方法调用onHandleEvent()方法进行事件的具体处理之前，会用弱引用保存webview所在的activity和fragment（如果有），便于在处理过程中使用

getHybridImpl()是获取activity和fragmnet中，其中一个实现了IHybrid接口的，fragment优先（因为存在把webview放在fragment中的情况）

### HybridEvent
有2个成员字段，均为String类型，分别是operationType和params，也就是要求H5发来的事件是如下形式的json字符串：
```
{
    "operationType": "xxxxx",
    "params": {
        "yyyy": "zzzz"
    }
} 
```
其中xxxxx是和H5约定好的匹配字段，params中存放另一个json字符串，放置一堆参数键值对供APP使用

内置toJsonString()方法获取json字符串，保留了toString()方法没有重写

### HybridEventHandlerManager
是LinkedHashMap的子类，单例，用上文讲到的operationType为key，BaseHybridEventHandler的子类对象为value，双向链表的形式保存所有处理H5消息的handler

addHandler()方法用于向manager添加handler，相同operationType的handler会互相覆盖，保留晚添加的(HashMap的key不可重复)

onLaunchActivityResult()由webview所在的activity或者fragment调用，接收需要由handler进行处理的activityResult，
manager内部顺序遍历所有handler，找到实现了IActivityResultHandler接口，并且launchCode相匹配的handler，把result分发过去

### NativeEventHandler
单例，没有什么好说的，就是用调用js方法的形式和H5进行通信（具体通信实现由activity负责），通信的json数据还是HybridEvent类型的结构

## 事件接口
涉及到：
1. IActivityResultHandler
2. IHybrid

### IActivityResultHandler
想要处理activity返回result的handler需要实现该接口

getLaunchCode()方法负责提供启动activity的launchCode，如果是startActivityForResult，则为requestCode，
如果是registerForActivityResult则需要自己保存一下launchCode，随后在收到result之后传入launchCode进行分发

onLaunchActivityResult()方法是收到了manager分发过来的result，进行处理

### IHybrid
由涉及处理H5消息的activity/fragment实现

launchActivityResultLauncher()方法，提供给handler调用，拉起另一个activity并获取结果，
handler通过getHybridImpl()方法获取到UI层的实例，然后调用该方法

loadUrlCallJs()方法由NativeEventHandler调用，告知activity需要调用JS方法了，由activity负责通信是为了兼容各种第三方WebView