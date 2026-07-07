# 母婴商城管理系统

一个基于 Spring Boot、MyBatis-Plus、MySQL 和 Vue2 的母婴商城全栈项目，包含前台商城展示、用户购物流程和后台管理端。

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
| 前台页面 | HTML, CSS, JavaScript, LayUI |
| 构建工具 | Maven, npm |
| 数据库 | MySQL |

## 核心功能

- 用户端：注册登录、商品浏览、商品收藏、购物车、订单下单、地址管理、商品评价
- 管理端：用户管理、商品分类管理、商品信息管理、订单管理、评论管理、资讯管理、客服管理
- 系统能力：分页查询、文件上传、统一响应结构、登录拦截、数据库初始化

## 本地运行

1. 安装 JDK 8、Maven、MySQL、Node.js。
2. 创建数据库 `muyingshangcheng`，导入 `database/schema.sql`。
3. 进入 `backend` 执行 `mvn spring-boot:run`。
4. 进入 `admin-frontend` 执行 `npm install && npm run serve`。

## 演示账号

| 角色 | 账号 | 密码 |
| --- | --- | --- |
| 管理员 | admin | admin |
| 普通用户 | 111 | 111 |

> 这是一个整理后的公开展示版本，后续会继续补充完整源码、接口说明和运行截图。
