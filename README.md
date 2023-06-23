# 简介
一个基于Kotlin+MVVM+Retrofit的原生APP开发框架
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
2. 仿照app/包名/api下的所有文件，编写适应你项目需求的代码，特别注意修改标注了todo的部分

# 框架介绍
## Activity
todo……
