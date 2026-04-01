# PersonalHomeService

Spring Boot 个人服务项目。

## 技术栈

- Java 23
- Spring Boot 3.2.3
- Maven

## 项目结构

```
PersonalHomeService/
├── pom.xml                 # Parent POM
└── app-starter/            # 启动模块
    ├── pom.xml
    └── src/main/java/      # 启动类
```

## 运行

```bash
mvn spring-boot:run -pl app-starter
```

服务启动后访问 http://localhost:8080

## 端点

| 端点 | 描述 |
|------|------|
| `GET /` | 返回 "Hello" |
| `GET /health` | 健康检查，返回 "UP" |

## 开发

本项目使用 [OpenSpec](https://github.com/charleswang1999/openspec) 进行变更管理。
