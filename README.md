# BusTicketSystem (汽车购票系统)

## 项目简介

BusTicketSystem 是一个基于 Spring Boot 的汽车购票系统，旨在为用户提供便捷的车票查询、预订、支付和管理功能。系统支持管理员、会员和普通用户的多角色操作，提供丰富的接口以满足不同用户的需求。

## 功能模块

### 用户模块

- **用户注册**：支持新用户注册。
- **用户登录**：支持用户登录并获取身份令牌。
- **用户信息管理**：支持用户信息查询、更新和删除。
- **用户状态管理**：支持用户状态的启用、禁用和批量设置。

### 车次管理模块

- **车次添加**：支持管理员添加新的车次信息。
- **车次查询**：支持分页查询车次信息。
- **车次更新**：支持管理员更新车次信息。
- **车次删除**：支持单个或批量删除车次信息。

### 订单管理模块

- **订单创建**：支持用户创建订单。
- **订单查询**：支持根据订单 ID 查询订单详情。
- **订单支付**：支持订单支付操作。
- **订单取消**：支持订单取消操作。
- **订单更新**：支持订单信息更新。
- **订单删除**：支持单个或批量删除订单。

### 公告管理模块

- **公告添加**：支持管理员发布公告。
- **公告查询**：支持分页查询公告信息。
- **公告更新**：支持管理员更新公告内容。
- **公告删除**：支持单个或批量删除公告。

### 留言管理模块

- **留言添加**：支持用户提交留言。
- **留言查询**：支持分页查询留言信息。
- **留言更新**：支持管理员更新留言内容。
- **留言删除**：支持单个或批量删除留言。

## 技术栈

- **后端框架**：Spring Boot
- **数据库**：MySQL
- **持久层框架**：MyBatis-Plus
- **安全框架**：Sa-Token
- **分页插件**：PageHelper
- **加密工具**：RSAUtil

## 接口文档

以下是系统提供的主要接口：

### 用户接口

- **POST** `/user/login`：用户登录
- **POST** `/user/register`：用户注册
- **GET** `/user/info`：获取用户信息
- **PUT** `/user/update`：更新用户信息
- **DELETE** `/user/delete`：删除用户
- **PUT** `/user/status`：设置用户状态
- **DELETE** `/user/batchDelete`：批量删除用户
- **PUT** `/user/batchSetStatus`：批量设置用户状态

### 车次接口

- **POST** `/busRoute/add`：添加车次
- **POST** `/busRoute/search`：查询车次
- **PUT** `/busRoute/update`：更新车次
- **DELETE** `/busRoute/delete`：删除车次
- **DELETE** `/busRoute/batchDelete`：批量删除车次

### 订单接口

- **POST** `/order/add`：创建订单
- **GET** `/order/searchOrder`：查询订单详情
- **POST** `/order/search`：查询订单
- **PUT** `/order/payOrder`：支付订单
- **PUT** `/order/cancelOrder`：取消订单
- **PUT** `/order/resetOrder`：重置订单
- **PUT** `/order/update`：更新订单
- **DELETE** `/order/delete`：删除订单
- **DELETE** `/order/batchDelete`：批量删除订单

### 公告接口

- **POST** `/announcement/add`：添加公告
- **POST** `/announcement/search`：查询公告
- **PUT** `/announcement/update`：更新公告
- **DELETE** `/announcement/delete`：删除公告
- **DELETE** `/announcement/batchDelete`：批量删除公告

### 留言接口

- **POST** `/message/add`：添加留言
- **POST** `/message/search`：查询留言
- **PUT** `/message/update`：更新留言
- **DELETE** `/message/delete`：删除留言
- **DELETE** `/message/batchDelete`：批量删除留言

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+

### 本地运行

1. 克隆项目到本地：

   ```sh
   git clone https://github.com/your-repo/bus-ticket-system.git
   ```

2. 配置数据库：

修改 src/main/resources/application.yml 中的数据库连接信息。

3. 安装依赖并运行项目：

```sh
mvn spring-boot:run 
```

访问接口：

默认接口地址为 <http://localhost:8080>

### 贡献

欢迎提交 Issue 或 Pull Request 来改进此项目。

### 许可证

本项目基于 Apache License 2.0 开源。 ```
