# Proposal: Use Docker MySQL

## 为什么

项目目前没有数据持久化能力，需要引入 MySQL 数据库支持。 通过 Docker 运行 MySQL 可以快速搭建本地开发环境，避免手动安装配置的复杂性。

## 变更内容

1. 添加 Docker Compose 配置，启动 MySQL 容器并暴露端口至本地
2. 在 Spring Boot 中添加 MySQL JDBC 依赖
3. 配置 Spring Boot 数据源连接 Docker MySQL
4. 添加数据库初始化脚本和健康检查

## 功能 (Capabilities)

### 新增功能
- `docker-mysql`: 使用 Docker Compose 运行 MySQL 容器，本地端口 3306
- `spring-data-access`: Spring Boot 数据访问层配置，支持 JdbcTemplate 或 JPA

### 修改功能
- (无)

## 影响

- 新增依赖：`spring-boot-starter-jdbc` (或 spring-boot-starter-data-jpa)
- 新增文件：`docker-compose.yml`
- 修改文件：`application.yml` (添加 datasource 配置)
- 端口：3306 (MySQL)
