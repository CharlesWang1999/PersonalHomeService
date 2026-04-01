## 1. 添加 Web 依赖

- [x] 1.1 在 `app-starter/pom.xml` 中添加 `spring-boot-starter-web` 依赖

## 2. 配置端口

- [x] 2.1 创建 `app-starter/src/main/resources/application.yml`
- [x] 2.2 在 `application.yml` 中配置 `server.port: 8080`

## 3. 实现 Controller

- [x] 3.1 创建 `HomeController.java` 包目录 `com/charles/controller`
- [x] 3.2 创建 `HomeController.java`，实现 `/` 端点返回简单文本
- [x] 3.3 在 `HomeController` 中添加 `/health` 端点返回 "UP"

## 4. 验证

- [x] 4.1 运行 `mvn clean compile` 验证编译通过
- [x] 4.2 启动应用并验证 8080 端口监听
- [x] 4.3 测试 `curl http://localhost:8080/` 返回 200
- [x] 4.4 测试 `curl http://localhost:8080/health` 返回 "UP"
