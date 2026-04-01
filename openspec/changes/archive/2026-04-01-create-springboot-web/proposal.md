## 为什么

需要为 Spring Boot 项目创建一个后端服务，使其能够在应用启动时自动监听 HTTP 请求。项目当前仅有基础结构，需要补充核心 Web 服务能力。

## 变更内容

- 创建 Spring Boot Web 服务
- 配置服务监听 8080 端口
- 添加健康检查端点 `/health`

## 功能 (Capabilities)

### 新增功能

- `web-server`: 创建 Web 服务，支持 HTTP 请求处理，监听 8080 端口

## 影响

- 新增 `spring-boot-starter-web` 依赖
- 新增 `HomeController` 处理根路径和健康检查
- 应用默认端口从 8080 提供 HTTP 服务
