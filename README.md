# CarMVVMSample

## Android Automotive架构示例应用/封装

在大多数车载系统应用架构中，一个完整的应用往往会包含三层：

- HMI
Human Machine Interface，显示UI信息，进行人机交互。

- Service
在系统后台进行数据处理，监控数据状态。

- SDK
根据业务逻辑`Service对`外暴露的通信接口，其他模块通过它来完成IPC通信。

当然并不是所有的应用都需要`Service`，只有不能长久的驻留在内存中，且需要监控系统数据和行为的应用才需要`Service`。

举个例子，系统的OTA需要一个`Service`在IVI的后台监控云服务或SOA接口的消息，然后完成升级包的下载等。也需要一个`HMI`显示升级的Release Note、确认用户是否同意升级等，这个`HMI`往往会被归纳在系统设置中。`Service`与`HMI`之间的IPC通信，则需要暴露一个`SDK`来完成，这个其他模块的`HMI`也可以通过这个`SDK`完成与`Service`的IPC通信。

反例则是，**Launcher** 可以长久的驻留在内存，所以它也就不需要`Service`和`SDK`。



Android Automotive 是 OS，其归属于 AOSP 项目，代码也在 Android OS 之中。在编译的时候选择 automotive 的编译 target 即可生成车载机使用的 AAOS。
它支持加载 Android Auto 的 App，即将 Android Phone 和 AAOS 车机仍旧能像 Android Auto 一样使用。同时支持 Android OS 的 App，即不适配车载机亦能运行在 AAOS 上。这可能也是主流互联网 App 无意适配 AAOS 的部分原因，同时导致适配车机 UI 的任务落到了车企身上。

官方提供了针对导航等几个场景提供了开发 Sample：
- https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fandroid%2Fcar-samples
其优势在于，其兼顾了 Phone 和 Automotive 两种开发场景。将 App 共通的 Car 部分放置在 Common Module 里，各自的逻辑放在独立的 Module 中。
![image](https://user-images.githubusercontent.com/65901383/216306008-6d794974-b8d3-42a2-b99c-d7ee1a94dfdb.png)
好处是编译 Phone Task 的话生成的 Apk 安装在 Phone 上，当其进入 Android Auto 模式之后会自动加载 Common 里的 Car 逻辑。而编译到 Automotive 的 Apk 可直接运行在 AAOS 上，以执行 Common 逻辑和特有的 Car 逻辑。
有点需要注意的是该 Sample 的 **Gradle 和 AGP 版本需要升级到最新**，才能编译通过。



参考
链接：https://www.jianshu.com/p/50a2ccb805f2
链接：https://juejin.cn/post/7110767099579990030
