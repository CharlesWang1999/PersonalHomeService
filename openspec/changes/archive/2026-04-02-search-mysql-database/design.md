## 上下文

HomeController 是 Spring Boot 应用的入口控制器，已存在 `/` 和 `/health` 端点。需要新增 `/data` 端点查询 MySQL 数据库中的 greeting 表。

## 目标 / 非目标

**目标：**
- 新增 `@GetMapping("/data")` 返回 id 最大的 greeting 记录的 message

**非目标：**
- 不涉及数据写入操作
- 不创建新的 Service 层（直接使用 JdbcTemplate）

## 决策

### 1. 直接使用 JdbcTemplate vs 创建 Repository
- **选择**：直接在 Controller 中使用 JdbcTemplate
- **理由**：功能简单，仅一个查询操作，无需过度工程化

### 2. SQL 查询
- **选择**：`SELECT message FROM greeting ORDER BY id DESC LIMIT 1`
- **理由**：获取最大 id 的记录最直接的方式

## 风险 / 权衡

| 风险 | 缓解措施 |
|------|----------|
| 空表导致异常 | 使用 Optional 或空值判断处理 |

## 实现步骤

1. 在 HomeController 注入 JdbcTemplate
2. 新增 `/data` 端点，查询最大 id 的 message
3. 如果无数据返回空字符串或提示信息
