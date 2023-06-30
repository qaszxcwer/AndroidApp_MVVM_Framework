# Net
分为2大块，框架部分和app部分（业务部分）

## 框架部分

### ApiException
自定义了一个异常类，字段为errorCode和errorMessage，用于app/BaseNetCallback中

### SingleLiveData
借助原子操作改写MutableLiveData，应用于多个网络请求（多线程场景）共用的回调中

比如说多个接口都会触发登录、触发出错提示，为了防止这些在极为接近的时间触发导致前一步的操作还没有做完就被覆盖，使用原子操作控制了observer.onChanged()的调用

## app部分（业务部分）
均在demo代码的app/api中

### ApiRequest
retrofit2的接口集合，详细的可以去看retrofit2的指南

### BaseResponse
接口返回的公共部分，和服务端的接口设计强相关

### BaseListResponse
列表接口的公共部分，在demo的使用中作为BaseResponse的result字段出现，即为把BaseListResponse填到BaseResponse的泛型里面去，同样是和服务端接口设计强相关的

### RetrofitClient
用于发起网络请求，详细的可以去看retrofit2的指南

如果需要把接口地址归类，或者业务复杂导致接口存在多种不同域名的情况，可以写多个接口文件（demo中为ApiRequest），然后对应创建实例，在发起网络请求时视情况调用

### BaseNetCallback
用于网络请求结果的处理
1. onResponse()和onFailure()分别接收正常结果和出错结果
2. 在onResponse()中筛选出符合业务需求的正常结果
3. 被筛掉的出错结果手动调用onFailure()统一处理
4. onFail()方法留给特殊情况重写使用
5. 无特殊情况则进入dealWithFailure()方法，按照你的业务需求进行编写
6. onSuccess()必须重写，按照业务需求编写接口请求成功情况下的代码