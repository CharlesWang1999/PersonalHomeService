## 新增需求

### 需求:项目结构初始化
项目必须具备基于 Spring Boot 3.x 的可运行基础结构，包含 Maven 多模块配置和启动入口。

#### 场景:根目录 POM 配置正确
- **当** 检查根目录 pom.xml 文件
- **那么** 必须包含 spring-boot-starter-parent 作为 parent
- **那么** 必须声明 app-starter 模块

#### 场景:app-starter 模块存在
- **当** 检查 app-starter 模块目录
- **那么** 必须存在 app-starter/pom.xml 文件
- **那么** 必须声明 spring-boot-starter 依赖

#### 场景:启动类配置正确
- **当** 检查 app-starter 模块中的 Java 启动类
- **那么** 必须使用 @SpringBootApplication 注解
- **那么** 必须位于 app-starter/src/main/java/ 目录下
