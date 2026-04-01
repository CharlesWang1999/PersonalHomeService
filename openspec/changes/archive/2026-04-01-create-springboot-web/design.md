## 上下文

项目为 Spring Boot 3.2.3 多模块 Maven 项目，根 pom.xml 管理版本，已存在 `app-starter` 模块包含基础启动类 `App.java`。当前仅依赖 `spring-boot-starter`，无 Web 能力。

## 目标 / 非目标

**目标：**
- 添加 Web 依赖使服务可监听 HTTP 请求
- 配置服务默认监听 8080 端口
- 提供 `/health` 健康检查端点
- 提供根路径 `/` 返回简单响应

**非目标：**
- 不实现数据库连接
- 不实现业务逻辑
- 不添加安全性（Security）

## 决策

| 决策 | 选择 | 理由 |
|------|------|------|
| Web 依赖 | `spring-boot-starter-web` | Spring Boot 推荐标准， 自动配置 Tomcat |
| 端口配置 | `application.yml` 设置 `server.port: 8080` | Spring Boot 标准方式，无需代码 |
| Controller | 创建 `HomeController` | 简单控制器处理 `/` 和 `/health` 路径 |

### 替代方案考虑

- **使用 Jetty 替代 Tomcat**：当前 `spring-boot-starter-web` 默认使用 Tomcat，保持默认以简化依赖
- **硬编码端口**：使用 `application.properties` 更灵活，支持环境变量覆盖

## 风险 / 权衡

| 风险 | 影响 | 缓解措施 |
|------|------|----------|
| 端口 8080 被占用 | 服务启动失败 | 提供明确错误信息，用户可修改端口 |

## 实现概述

1. 在 `app-starter/pom.xml` 添加 `spring-boot-starter-web` 依赖
2. 创建 `src/main/resources/application.yml`，配置 `server.port: 8080`
3. 创建 `HomeController.java`，实现 `/` 和 `/health` 端点
