## 新增需求

### 需求:Docker MySQL 容器必须可通过 docker compose 启动

系统必须提供 `docker-compose.yml` 配置文件，使用 Docker 启动 MySQL 服务。

#### 场景:成功启动 MySQL 容器
- **当** 开发者在项目根目录执行 `docker compose up -d`
- **那么** MySQL 容器必须成功启动，端口 3306 必须映射至本地

#### 场景:MySQL 容器必须配置持久化存储
- **当** Docker 容器重启后
- **那么** 数据库数据必须保留

#### 场景:初始化脚本必须自动执行
- **当** MySQL 容器首次启动时
- **那么** `init.sql` 脚本必须自动执行

### 需求:MySQL 配置必须可自定义

`docker-compose.yml` 必须通过环境变量支持以下配置：
- `MYSQL_ROOT_PASSWORD` - Root 用户密码
- `MYSQL_DATABASE` - 创建的默认数据库名

#### 场景:使用自定义密码连接
- **当** 用户配置了自定义的 `MYSQL_ROOT_PASSWORD`
- **那么** 必须能够使用该密码连接 MySQL
