## 1. Docker MySQL 配置

- [x] 1.1 创建 `docker-compose.yml`，配置 MySQL 8.0 镜像，端口映射 3306:3306
- [x] 1.2 创建 `init.sql` 初始化脚本，创建测试数据库和示例表
- [x] 1.3 配置 Docker volume 持久化 MySQL 数据

## 2. Spring Boot 数据访问依赖

- [x] 2.1 在 `pom.xml` 中添加 `spring-boot-starter-jdbc` 依赖
- [x] 2.2 在 `pom.xml` 中添加 `mysql-connector-java` 依赖

## 3. Spring Boot 数据源配置

- [x] 3.1 在 `application.yml` 中配置 MySQL 数据源 (localhost:3306)
- [x] 3.2 配置 HikariCP 连接池参数
- [x] 3.3 配置 `schema.sql` 和 `data.sql` 自动初始化
