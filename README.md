# dtPlan
[![](https://img.shields.io/badge/SNAPSHOT-1.0-green)](https://github.com/colinguangyi/dtPlan) [![](https://img.shields.io/badge/JDK-1.8-yellowgreen)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

分布式事务设计尝试

>###client

一个模拟客户端，用来发送分布式事务请求

>###eureka

eureka注册中心服务

>###server

实现具体事务的业务逻辑，本示例将2个事务操作都放在此服务中，一个插入事务一个更新事务。

>###server-api

一个jar包，用来分装feign请求的service以及一些实体bean，请求bean等信息

