## 为什么

当前项目为空结构，需要将其初始化为 Spring Boot 项目，以便后续业务开发。app-starter 模块将作为应用的启动入口模块。

## 变更内容

将现有空项目改造为 Spring Boot 多模块 Maven 项目：

- **新增** app-starter 模块，包含 Spring Boot 启动类和主配置
- **新增** parent pom.xml 配置 Spring Boot 依赖管理
- **保留** 现有 .gitignore 和 .idea 配置文件

## 功能 (Capabilities)

### 新增功能

- `springboot-init`: 初始化 Spring Boot 项目基础结构，包括 parent POM 和 app-starter 启动模块

### 修改功能

<!-- 无现有功能变更 -->

## 影响

- 新增 `app-starter/` 模块目录（含 pom.xml 和启动类）
- 修改根目录 `pom.xml`，添加 Spring Boot parent 配置和模块声明
- `.gitignore` 和 `.idea/` 配置保持不变
