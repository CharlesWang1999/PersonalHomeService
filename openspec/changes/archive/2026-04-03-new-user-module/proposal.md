## 为什么

需要一个独立的 `app-user` 模块来实现用户生命周期管理，包括用户注册、登录认证和管理员对用户的管理功能。采用 DDD（领域驱动设计）架构确保业务逻辑的内聚性和可维护性，同时通过 JPA 实现数据持久化。

## 变更内容

新增 `app-user` 模块，包含以下功能：

- **用户注册**：新用户通过邮箱/手机号进行注册
- **用户登录**：基于 JWT 的无状态认证
- **管理员用户管理**：管理员可查看、启用/禁用、删除用户

## 功能 (Capabilities)

### 新增功能

- `user-registration`: 用户注册功能，包括注册信息校验、密码加密存储
- `user-authentication`: 用户登录与 JWT 令牌发放
- `admin-user-management`: 管理员对用户的增删改查管理

### 修改功能

无

## 影响

- 新增 `app-user` 模块，采用 DDD 分层架构（domain / application / infrastructure / interface）
- 使用 Spring Data JPA 进行数据持久化
- Domain 层保持纯净，不依赖任何技术框架
- 现有 `app-starter` 模块不受影响
