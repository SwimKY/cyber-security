# cyber-security
## 基于SpringBoot、Mybatis-plus开发网络安全主题的网站
## 说明：
- 该网站主题网络安全，背景音乐取自《Star Wars》；
- 网页后台能够根据需求实现定时（默认凌晨3点）爬取网络安全相关新闻存入数据库和Elasticsearch中；
- 新闻获取从数据库中获取数据，为了提高访问速度增加Redis缓存；
- 搜索数据均来自于搜索引擎并能够实现关键字高亮，为提高中文搜索精确度使用ik分词器插件；

### 技术实现：
- 后端框架SpringBoot+Mybatis-plus、
- 集成Elasticsearch搜索引擎、Redis缓存、使用Druid数据库连接池、
- 爬虫jsoup框架、日志使用log4j框架等；
- 前端使用thymeleaf、Vue+axios、jq、页面中的3D地球地图基于百度地图实现等；
- 数据库使用Docker远程部署，
- 支持Swagger2测试接口文档项目启动后访问http://localhost:8004/swagger-ui.html

项目启动注意事项：项目启动前需要提前启动本地redis、Elasticsearch，配置数据库地址等
启动完成后访问http://localhost:8004；
