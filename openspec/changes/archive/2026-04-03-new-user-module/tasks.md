## 1. 项目结构搭建

- [x] 1.1 创建 app-user 模块根目录及 DDD 四层包结构
- [x] 1.2 在 domain/model 下创建 User 领域实体（仅包含 id, email, passwordHash, accountDisabled, createdAt, updatedAt, deleted 字段，无框架注解）
- [x] 1.3 在 domain/repository 下创建 UserRepository 接口（仅方法声明，无实现）
- [x] 1.4 在 domain/service 下创建 UserDomainService 领域服务类
- [x] 1.5 在 application/service 下创建 UserApplicationService 应用服务类
- [x] 1.6 在 infrastructure/persistence 下创建 JPA 实现类（UserJpaEntity, UserJpaRepository）
- [x] 1.7 在 infrastructure/security 下创建 JwtTokenProvider、JwtAuthenticationFilter
- [x] 1.8 在 interface/controller 下创建 UserController、AuthController

## 2. 用户注册功能

- [x] 2.1 在 application/dto 下创建 RegisterRequest、RegisterResponse DTO
- [x] 2.2 在 application/port 下创建 PasswordEncoder 端口接口
- [x] 2.3 在 infrastructure/security 下实现 BCryptPasswordEncoder
- [x] 2.4 在 UserDomainService 中实现注册校验逻辑（邮箱唯一性、密码复杂度）
- [x] 2.5 在 UserApplicationService 中实现注册用例，调用领域服务
- [x] 2.6 在 AuthController 中实现 POST /api/auth/register 接口
- [x] 2.7 添加邮箱格式校验（RFC 5322 标准）
- [x] 2.8 添加密码复杂度校验（至少 8 字符，大小写字母和数字）

## 3. 用户登录功能

- [x] 3.1 在 application/dto 下创建 LoginRequest、LoginResponse DTO（包含 Access Token 和 Refresh Token）
- [x] 3.2 在 UserDomainService 中实现登录校验逻辑
- [x] 3.3 在 JwtTokenProvider 中实现令牌生成逻辑（包含 userId、exp、iat claims）
- [x] 3.4 在 JwtTokenProvider 中实现令牌解析和校验逻辑
- [x] 3.5 在 UserApplicationService 中实现登录用例，调用领域服务和 JWT Provider
- [x] 3.6 在 AuthController 中实现 POST /api/auth/login 接口
- [x] 3.7 在 AuthController 中实现 POST /api/auth/refresh 接口（刷新令牌）

## 4. 管理员用户管理功能

- [x] 4.1 在 application/dto 下创建 UserDetailResponse、UserListResponse、PagedResponse DTO
- [x] 4.2 在 UserApplicationService 中实现查询用户列表用例（分页支持）
- [x] 4.3 在 UserApplicationService 中实现查询用户详情用例
- [x] 4.4 在 UserApplicationService 中实现禁用/启用用户用例
- [x] 4.5 在 UserApplicationService 中实现删除用户用例（软删除）
- [x] 4.6 在 UserController 中实现 GET /api/admin/users 分页查询接口
- [x] 4.7 在 UserController 中实现 GET /api/admin/users/{id} 详情接口
- [x] 4.8 在 UserController 中实现 PATCH /api/admin/users/{id}/disable 禁用接口
- [x] 4.9 在 UserController 中实现 PATCH /api/admin/users/{id}/enable 启用接口
- [x] 4.10 在 UserController 中实现 DELETE /api/admin/users/{id} 删除接口

## 5. Domain 层纯净性保障

- [x] 5.1 添加代码审查规则，禁止 domain 包下 import javax.persistence、org.springframework.stereotype
- [x] 5.2 在 domain/model 创建后验证 User 实体不含任何框架注解
- [x] 5.3 编写 domain 层单元测试（独立于 Spring 容器）

## 6. JWT 安全配置

- [x] 6.1 在 application.yml 中配置 JWT 密钥（通过环境变量注入，禁止硬编码）
- [x] 6.2 在 JwtTokenProvider 中实现令牌过期校验
- [x] 6.3 在 JwtAuthenticationFilter 中实现请求拦截和用户上下文注入
- [x] 6.4 实现刷新令牌存储（数据库表或 Redis）
- [x] 6.5 实现刷新令牌主动失效机制
