# PersonalHomeService

Spring Boot 个人服务项目。

## 技术栈

- Java 23
- Spring Boot 3.2.3
- Maven
- MySQL 8.0 (via Docker)

## 项目结构

```
PersonalHomeService/
├── pom.xml                 # Parent POM
├── docker-compose.yml       # MySQL 容器配置
├── init.sql                 # MySQL 初始化脚本
├── app-starter/            # 启动模块
│   ├── pom.xml
│   └── src/main/java/       # 启动类
└── app-user/                # 用户模块 (DDD 架构)
    ├── pom.xml
    └── src/main/java/com/charles/user/
        ├── domain/          # 领域层（纯净，无框架依赖）
        ├── application/      # 应用层
        ├── infrastructure/    # 基础设施层
        └── web/              # 接口层
```

## 模块

### app-starter

启动模块，提供基础 HTTP 服务。

### app-user

用户管理模块，采用 DDD（领域驱动设计）架构：

- **用户注册**：邮箱/密码注册，密码 BCrypt 加密
- **用户登录**：JWT 无状态认证
- **管理员用户管理**：分页查询、详情、启用/禁用、删除

#### API 端点

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | /api/auth/register | 用户注册 |
| POST | /api/auth/login | 用户登录 |
| POST | /api/auth/refresh | 刷新令牌 |
| GET | /api/admin/users | 分页查询用户 |
| GET | /api/admin/users/{id} | 用户详情 |
| PATCH | /api/admin/users/{id}/disable | 禁用用户 |
| PATCH | /api/admin/users/{id}/enable | 启用用户 |
| DELETE | /api/admin/users/{id} | 删除用户（软删除） |

#### DDD 分层

```
app-user/
├── domain/           # 领域层（纯净，无框架依赖）
│   ├── model/        # 领域实体
│   ├── repository/   # 仓储接口
│   ├── service/      # 领域服务
│   └── event/        # 领域事件
├── application/      # 应用层
│   ├── dto/          # 数据传输对象
│   ├── port/         # 端口接口
│   └── service/      # 应用服务
├── infrastructure/   # 基础设施层
│   ├── config/       # 配置类
│   ├── persistence/  # JPA 持久化
│   └── security/     # JWT 安全
└── web/              # 接口层
    └── controller/   # REST 控制器
```

## 运行

### 1. 启动 MySQL

```bash
docker compose up -d
```

### 2. 启动应用

```bash
mvn spring-boot:run -pl app-starter
```

服务启动后访问 http://localhost:8080

### 启动 app-user 模块

```bash
mvn spring-boot:run -pl app-user
```

app-user 默认运行在 http://localhost:8081

## 环境变量

| 变量 | 默认值 | 说明 |
|------|--------|------|
| DATABASE_URL | jdbc:mysql://localhost:3306/personal_home | 数据库连接 |
| DATABASE_USERNAME | root | 数据库用户名 |
| DATABASE_PASSWORD | root | 数据库密码 |
| JWT_SECRET | (内嵌默认值) | JWT 密钥（生产环境必须设置） |
| JWT_ACCESS_TOKEN_VALIDITY_MS | 3600000 | 访问令牌有效期（1小时） |
| JWT_REFRESH_TOKEN_VALIDITY_MS | 604800000 | 刷新令牌有效期（7天） |
| APP_USER_PORT | 8081 | app-user 服务端口 |

## 端点

| 端点 | 描述 |
|------|------|
| `GET /` | 返回 "Hello" |
| `GET /health` | 健康检查，返回 "UP" |
| `GET /data` | 返回数据库中 id 最大的 greeting 记录的 message |

## 数据库

- **端口**: 3306 (本地)
- **数据库名**: personal_home
- **用户名**: root
- **密码**: rootpassword

## 开发

本项目使用 [OpenSpec](https://github.com/charleswang1999/openspec) 进行变更管理。
