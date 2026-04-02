## 新增需求

### 需求:GET /data 端点必须返回 id 最大的 greeting 记录

系统必须提供 `/data` HTTP GET 端点，查询 greeting 表中 id 最大的记录的 message 字段并返回。

#### 场景:成功返回 message
- **当** 客户端发送 GET 请求至 `/data` 且数据库中存在记录
- **那么** 系统必须返回 id 最大的 greeting 记录的 message 值

#### 场景:无数据时返回空字符串
- **当** 客户端发送 GET 请求至 `/data` 且数据库中无记录
- **那么** 系统必须返回空字符串 ""
