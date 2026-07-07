# 母婴商城管理系统

一个基于 Spring Boot、MyBatis-Plus、MySQL 和 Vue2 的母婴商城全栈项目，包含用户购物流程和后台管理端。项目适合作为 Java Web / Spring Boot 方向的作品展示，重点体现后端接口开发、数据库表设计、后台管理功能和前后端联调能力。

## 项目亮点

- 前后端分离结构，后端提供 REST API，后台管理端基于 Vue2 + Element UI
- 覆盖电商常见业务流程：商品、分类、购物车、订单、收货地址、评价、收藏、资讯、客服会话
- 使用 MyBatis-Plus 完成基础 CRUD、分页查询、条件过滤和数据持久化
- 登录态通过 token 拦截器控制，区分普通用户和管理员操作入口
- 提供数据库初始化脚本，便于本地快速运行和演示
- 已清理构建产物、依赖目录和本地敏感配置，适合公开展示

## 技术栈

| 模块 | 技术 |
| --- | --- |
| 后端 | Spring Boot 2.2.2, MyBatis-Plus, MySQL, Shiro |
| 后台前端 | Vue2, Vue Router, Element UI, Axios |
| 构建工具 | Maven, npm |
| 数据库 | MySQL |

## 目录结构

```text
.
├── backend/          # Spring Boot 后端服务
├── admin-frontend/   # Vue 后台管理端核心源码
├── database/         # 数据库初始化脚本
└── docs/             # 项目说明材料
```

## 核心功能

- 用户端：注册登录、商品浏览、商品收藏、购物车、订单下单、地址管理、商品评价
- 管理端：用户管理、商品分类管理、商品信息管理、订单管理、评论管理、资讯管理、客服管理
- 系统能力：分页查询、文件上传、统一响应结构、登录拦截、数据库初始化

## 本地运行

### 1. 准备环境

- JDK 8
- Maven 3.x
- MySQL 5.7+ 或 8.x
- Node.js 12/14 推荐用于后台前端

### 2. 初始化数据库

创建数据库：

```sql
CREATE DATABASE muyingshangcheng DEFAULT CHARACTER SET utf8mb4;
```

导入脚本：

```text
database/schema.sql
```

### 3. 启动后端

```bash
cd backend
mvn spring-boot:run
```

默认服务地址：

```text
http://localhost:8080/muyingshangcheng
```

### 4. 启动后台管理端

```bash
cd admin-frontend
npm install
npm run serve
```

## 演示账号

| 角色 | 账号 | 密码 |
| --- | --- | --- |
| 管理员 | admin | admin |
| 普通用户 | 111 | 111 |

演示数据仅用于本地运行和功能展示。

## 项目说明

这个项目体现了 Java Web 全栈项目的基本开发流程：从数据库建表、后端接口、权限拦截、业务模块 CRUD，到前端管理页面联调。当前仓库为适配 GitHub 网页上传限制的展示版，保留了核心源码、数据库脚本和项目说明。

## License

MIT License
