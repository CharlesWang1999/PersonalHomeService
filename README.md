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
└── app-starter/            # 启动模块
    ├── pom.xml
    └── src/main/java/      # 启动类
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
