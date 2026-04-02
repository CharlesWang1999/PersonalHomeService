## 新增需求

### 需求:Spring Boot 必须配置 MySQL 数据源

Spring Boot 应用必须在 `application.yml` 中配置 MySQL 数据源，连接至本地 3306 端口。

#### 场景:数据源配置正确
- **当** Spring Boot 应用启动
- **那么** 数据源必须成功连接至 `localhost:3306` 的 MySQL

#### 场景:连接池使用 HikariCP
- **当** 数据源被创建时
- **那么** 必须使用 HikariCP 连接池，且默认配置应满足开发需求

### 需求:Spring Boot 必须添加 MySQL JDBC 依赖

项目 `pom.xml` 必须包含 MySQL JDBC 驱动和 Spring JDBC 依赖。

#### 场景:包含必要依赖
- **当** 执行 `mvn dependency:tree`
- **那么** 必须包含 `mysql-connector-java` 和 `spring-boot-starter-jdbc`

### 需求:Spring Boot 必须支持数据库初始化

应用启动时必须支持自动执行数据库初始化脚本。

#### 场景:应用启动时初始化数据库
- **当** Spring Boot 应用启动且 `spring.sql.init.mode` 为 `always`
- **那么** `schema.sql` 和 `data.sql` 必须被自动执行
