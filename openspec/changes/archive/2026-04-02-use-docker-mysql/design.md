## 上下文

项目使用 Spring Boot 构建 Web 服务，当前无数据库支持。需要添加 MySQL 作为数据存储，并通过 Docker 简化本地开发环境的搭建。

当前技术栈：
- Spring Boot 3.x (Java 23)
- Maven 多模块项目
- 现有模块：`app-starter`

## 目标 / 非目标

**目标：**
- 通过 Docker Compose 启动 MySQL 容器
- 将 MySQL 端口 (3306) 映射至本地
- Spring Boot 通过 JDBC 连接 MySQL
- 容器启动后可立即进行数据读写

**非目标：**
- 不在此次变更中创建业务表结构（使用简单的初始化脚本）
- 不配置生产级 MySQL（主从、备份等）

## 决策

### 1. Docker Compose vs 直接运行容器
- **选择**：Docker Compose
- **理由**：声明式配置，版本可控，易于团队共享

### 2. MySQL 镜像版本
- **选择**：`mysql:8.0`
- **理由**：主流稳定版本，Spring Boot 兼容性好

### 3. 数据源配置
- **选择**：Spring Boot `spring-boot-starter-jdbc` + HikariCP 连接池
- **理由**：轻量级，无需 JPA 的复杂性，直接控制 SQL

### 4. 端口映射
- MySQL 容器内部端口：3306
- 映射至本地端口：3306

### 5. 初始化脚本
- 使用 `docker-compose.yml` 中 `MYSQL_ROOT_PASSWORD` 设置密码
- 挂载 `init.sql` 至 `/docker-entrypoint-initdb.d/` 自动执行

## 风险 / 权衡

| 风险 | 缓解措施 |
|------|----------|
| Docker 内存占用 | 分配 1GB+ 内存给 Docker |
| 端口冲突 | 确认本地 3306 端口未被占用 |
| 数据持久化 | 使用 Docker volume 持久化数据 |

## 迁移计划

1. 添加 `docker-compose.yml` 和 `init.sql`
2. 添加 Maven 依赖 `spring-boot-starter-jdbc` 和 MySQL driver
3. 配置 `application.yml` 数据源
4. 用户执行 `docker compose up -d` 启动 MySQL
5. 运行 `mvn spring-boot:run` 启动应用
