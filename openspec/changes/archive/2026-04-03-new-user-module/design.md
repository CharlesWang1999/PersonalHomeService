## 上下文

app-user 模块是一个新增的用户管理模块，采用 DDD（领域驱动设计）架构。该模块负责用户注册、登录认证和管理员用户管理功能。

**技术约束：**
- 使用 Spring Data JPA 进行数据持久化
- Domain 层必须保持纯净，禁止依赖任何技术框架（如 Spring、hibernate）
- 使用 JWT 实现无状态认证

## 目标 / 非目标

**目标：**
- 建立清晰的 DDD 分层架构（domain / application / infrastructure / interface）
- 确保 Domain 层的纯净性，业务逻辑不耦合技术实现
- 实现用户注册、登录、管理员管理三大功能
- 通过 JPA 注解在 Infrastructure 层完成持久化适配

**非目标：**
- 不实现 OAuth2 第三方登录
- 不实现用户权限细粒度控制（RBAC）
- 不与其他模块进行跨模块调用（app-user 为独立模块）

## 决策

### 1. DDD 分层结构

采用四层架构：

```
app-user/
├── domain/           # 领域层（纯净，无框架依赖）
│   ├── model/        # 领域实体、值对象
│   ├── repository/   # 仓储接口（仅接口定义）
│   ├── service/      # 领域服务
│   └── event/        # 领域事件
├── application/      # 应用层
│   ├── service/      # 应用服务（用例编排）
│   ├── dto/          # 数据传输对象
│   └── port/         # 端口接口（入站/出站）
├── infrastructure/   # 基础设施层
│   ├── persistence/   # JPA 持久化实现
│   ├── security/      # JWT 安全实现
│   └── config/       # 基础设施配置
└── interface/        # 接口层
    └── controller/   # REST API 控制器
```

**理由：** 清晰的职责分离确保 Domain 层可独立测试和演进，Infrastructure 层可替换。

### 2. Domain 层纯净性策略

**规则：**
- Domain 实体只使用 Java 基础类型和 JDK 标准库
- 禁止在 domain 包下引入 `javax.persistence`、`org.springframework.stereotype` 等框架注解
- 仓储接口定义在 domain 层，实现类在 infrastructure 层
- 使用 Domain Event 在应用层完成事务边界控制

**实现方式：**
- 实体 ID 使用 `String` 或 `Long`，由应用层生成
- 领域服务通过依赖注入接收仓储接口（在 application 层注入）
- 持久化注解（`@Entity`、`@Table`）放在 infrastructure 层的 JPA 实现类上

### 3. JPA 持久化策略

**实体映射：**
- User 实体：id, email, passwordHash, accountDisabled, createdAt, updatedAt, deleted
- 使用 Spring Data JPA Repository，接口定义在 infrastructure 层
- 软删除通过 `deleted` 字段实现，查询时自动过滤

### 4. JWT 认证方案

- 访问令牌（Access Token）：有效期 1 小时，包含用户 ID 和角色
- 刷新令牌（Refresh Token）：有效期 7 天，用于续期
- 令牌存储在 HTTP Header `Authorization: Bearer <token>`
- 密钥管理：通过环境变量或配置中心注入

## 风险 / 权衡

| 风险 | 缓解措施 |
|------|----------|
| Domain 层纯净性难以维护 | 通过代码审查规则强制检查，禁止 import Spring/JPA 注解 |
| JWT 令牌刷新逻辑复杂 | 刷新令牌存储在数据库，支持主动失效 |
| DDD 初期学习成本高 | 参考现有的 DDD 项目结构，从简单实体开始 |

## 开放问题

- [ ] 管理员角色是否需要细分（超级管理员 vs 普通管理员）？
- [ ] 用户注册是否需要邮件/短信验证码激活？
- [ ] JWT 密钥轮换策略？
