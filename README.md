# CarMVVMSample

## 车载应用MVVM架构示例应用/封装


**`2023-02-09`：分离`service`和`SDK`的封装示例到 [CarServiceSample](https://github.com/ak-ing/CarServiceSample)**
****
**此demo为MVVM示例架构和`SDK`使用方实例，请自行组合**


在大多数车载系统应用架构中，一个完整的应用往往会包含三层：

- `HMI`
Human Machine Interface，显示UI信息，进行人机交互。

- `Service`
在系统后台进行数据处理，监控数据状态。

- `SDK`
根据业务逻辑`Service对`外暴露的通信接口，其他模块通过它来完成IPC通信。

当然并不是所有的应用都需要`Service`，只有不能长久的驻留在内存中，且需要监控系统数据和行为的应用才需要`Service`。

举个例子，系统的OTA需要一个`Service`在IVI的后台监控云服务或SOA接口的消息，然后完成升级包的下载等。也需要一个`HMI`显示升级的Release Note、确认用户是否同意升级等，这个`HMI`往往会被归纳在系统设置中。`Service`与`HMI`之间的IPC通信，则需要暴露一个`SDK`来完成，这个其他模块的`HMI`也可以通过这个`SDK`完成与`Service`的IPC通信。

反例则是，**Launcher** 可以长久的驻留在内存，所以它也就不需要`Service`和`SDK`。



Android Automotive 是 OS，其归属于 AOSP 项目，代码也在 Android OS 之中；Android Automotive 支持专为 Android 打造的应用，以及专为 Android Auto 打造的应用。

官方提供了针对导航等几个场景提供了开发 Sample：
- https://github.com/android/car-samples
其优势在于，其兼顾了 Phone 和 Automotive 两种开发场景。将 App 共通的 Car 部分放置在 Common Module 里，各自的逻辑放在独立的 Module 中。
![image](https://user-images.githubusercontent.com/65901383/216306008-6d794974-b8d3-42a2-b99c-d7ee1a94dfdb.png)
好处是编译 Phone Task 的话生成的 Apk 安装在 Phone 上，当其进入 Android Auto 模式之后会自动加载 Common 里的 Car 逻辑。而编译到 Automotive 的 Apk 可直接运行在 AAOS 上，以执行 Common 逻辑和特有的 Car 逻辑。
有点需要注意的是该 Sample 的 **Gradle 和 AGP 版本需要升级到最新**，才能编译通过。


将 Android Automotive 应用程序的通用组件分离到一个通用模块中是一种常见的做法，同时将手机和汽车场景的特定逻辑保留在单独的模块中。这允许更大的模块化和代码的重用，使将来维护和更新应用程序更容易。

例如，公共模块可以包含共享的 UI 组件、实用功能以及手机和汽车场景使用的任何其他代码。同时，每个特定于场景的模块将包含特定于该场景的代码，例如不同的导航或控制方法，以及任何其他特定于平台的代码。

这种方法有助于保持明确的关注点分离，并使更新应用程序或向应用程序添加新功能变得更加容易，同时确保应用程序继续在手机和汽车平台上正常运行。


参考
链接：https://www.jianshu.com/p/50a2ccb805f2
链接：https://juejin.cn/post/7110767099579990030
