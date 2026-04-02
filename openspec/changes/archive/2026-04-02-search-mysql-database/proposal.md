# Proposal: Search MySQL Database

## 为什么

通过 Docker 运行了 MySQL 数据库，并初始化了 greeting 表。需要新增一个 API 接口查询数据库中的数据。

## 变更内容

1. 在 HomeController 新增 `/data` 接口
2. 通过 JdbcTemplate 查询 greeting 表中 id 最大的记录
3. 返回 message 字段值

## 功能 (Capabilities)

### 新增功能
- `greeting-query`: 查询 greeting 表中 id 最大的记录并返回其 message

### 修改功能
- (无)

## 影响

- 修改文件：`HomeController.java`
- 新增依赖：无（spring-boot-starter-jdbc 已在 use-docker-mysql 中添加）
