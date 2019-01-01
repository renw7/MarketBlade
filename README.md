## 简介
SpringBlade 2.0 是一个基于 Spring Boot 2 & Spring Cloud Finchley & Mybatis 等核心技术，用于快速构建中大型系统的基础框架。

注意事项
* 注册中心为 Consul 
* 基于 SpringBoot2.x 版本 以及 SpringCloud Finchley 版本

技术选型&文档
* Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
* Spring Cloud（[查看Spring Cloud学习&使用指南](https://springcloud.cc/)）
* Mybatis-Plus（[查看官方文档](https://mp.baomidou.com/guide/)）
* JsonWebToken（[查看官方文档](https://jwt.io/)） 

## 工程结构
``` 
bladex
├── blade-auth -- 授权服务提供
├── blade-common -- 常用工具封装包
├── blade-gateway -- Spring Cloud 网关
├── blade-ops -- 运维中心
├    ├── blade-admin -- spring-cloud后台管理
├    ├── blade-codegen -- 代码生成
├    └── blade-config-server -- 配置中心
├── blade-service -- 业务模块
├    ├── blade-desk -- 工作台模块 
├    ├── blade-log -- 日志模块 
├    ├── blade-system -- 系统模块 
├    └── blade-user -- 用户模块 
├── blade-service-api -- 业务模块api封装
├    ├── blade-desk-api -- 工作台api 
├    ├── blade-dict-api -- 字典api 
├    ├── blade-system-api -- 系统api 
└──  └── blade-user-api -- 用户api 
```

# 开源协议
Apache Licence 2.0 （[英文原文](http://www.apache.org/licenses/LICENSE-2.0.html)）

Apache Licence是著名的非盈利开源组织Apache采用的协议。该协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）。

需要满足的条件如下：

* 需要给代码的用户一份Apache Licence

* 如果你修改了代码，需要在被修改的文件中说明。

* 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。

* 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。

Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售。

## 用户权益
* 允许免费用于学习、毕设、公司项目、私活等。
* 代码文件需保留相关license信息。

## 禁止事项
* 直接将本项目挂淘宝等商业平台出售。
* 非界面代码50%以上相似度的二次开源，二次开源需先联系作者。

注意：若禁止条款被发现有权追讨19999的授权费。

## 如何启动
* 开启consul，redis，mysql
* fork ([bladex-config](https://gitee.com/smallc/bladex-config))项目，修改数据库等相关链接
* 修改 `blade-config-server` 工程 `bootstrap.yml中config.server.git.uri` 的值为新fork项目的地址
* 先启动 `blade-config-server`
* 再依次启动其余模块

## 技术文档
* 即将发布，敬请期待

## 注
* SpringBlade前端UI项目地址：[Sword](https://gitee.com/smallc/Sword)
* SpringBlade交流群：`477853168`


# 界面一览
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-k8s.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-traefik.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-traefik-health.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-harbor.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-consul.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-consul-nodes1.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-consul-nodes2.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-admin1.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-admin2.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-swagger1.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/springblade-swagger2.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-main.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-menu.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-menu-edit.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-menu-icon.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-role.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-user.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-dict.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-locale-cn.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-locale-us.png "业务系统")
![业务系统](https://raw.githubusercontent.com/chillzhuang/blade-tool/master/pic/sword-log.png "业务系统")