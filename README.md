# springboot-app-service

使用springboot搭建的音乐，电影，书栈，视频教程，明日头条app的后台项目，所有数据来自互联网，使用python爬虫抓取，涉及，负载均衡，redis缓存，JwtToken权限验证，拦截器，日志记录，erauka服务治理，mybatis,spring-data-jpa,swagger等，持续更新中...

================================APP界面预览================================
![电影app整体预览](./%E7%94%B5%E5%BD%B1app%E6%95%B4%E4%BD%93%E9%A2%84%E8%A7%88.jpg)
![在线音乐app整体预览](./%E5%9C%A8%E7%BA%BF%E9%9F%B3%E4%B9%90app%E6%95%B4%E4%BD%93%E9%A2%84%E8%A7%88.jpg)
![明日头条app整体效果图（待更新）](./%E6%98%8E%E6%97%A5%E5%A4%B4%E6%9D%A1app%E6%95%B4%E4%BD%93%E6%95%88%E6%9E%9C%E5%9B%BE%EF%BC%88%E5%BE%85%E6%9B%B4%E6%96%B0%EF%BC%89.jpg)
![发说说](./发说说.jpg)
![电影圈](./电影圈.jpg)
================================APP界面预览================================


==========================Eureka高可用配置配置==========================

![Eureka高可用配置1](./Eureka高可用配置1.png)
![Eureka高可用配置2](./Eureka高可用配置2.png)
配置参数1：
name: EurekaCenterApplication2
main class: com.player.eurekacenter.EurekaCenterApplication
VM options: -DPORT=4001 -DEUREKA_SERVER=http://localhost:4002/euraka/  -DEUREKA_DOMAIN=euraka01

配置参数2：
name: EurekaCenterApplication2
main class: com.player.eurekacenter.EurekaCenterApplication
VM options: -DPORT=4002 -DEUREKA_SERVER=http://localhost:4001/euraka/  -DEUREKA_DOMAIN=euraka01

==========================Eureka高可用配置配置==========================

=============================项目启动顺序=============================

![启动顺序](./启动顺序.jpg)

先按顺序启动前面三个，其他按需启动

=============================项目启动顺序=============================


================================sql实例===============================

![app首页](https://raw.githubusercontent.com/wuyuanwuhui99/springboot-app-service/main/mysql.png)
sql数据来自于python爬虫项目，自动爬取第三方电影网站，由于涉及到网络信息安全问题，爬虫项目暂未公开

================================sql实例===============================


================================手机UI项目================================
flutter在线电影项目: https://github.com/wuyuanwuhui99/flutter-movie-app-ui

android安卓在线电影项目: https://github.com/wuyuanwuhui99/android-java-movie-app-ui

harmony鸿蒙在线电影：https://github.com/wuyuanwuhui99/Harmony_movie_app_ui

react native版本在线电影: https://github.com/wuyuanwuhui99/react-native-app-ui

harmony鸿蒙在线电影项目：https://github.com/wuyuanwuhui99/Harmony_movie_app_ui

vue3+ts电影圈项目：https://github.com/wuyuanwuhui99/vue3-ts-movie-circle-app-ui

vue2在线音乐项目：https://github.com/wuyuanwuhui99/vue-music-app-ui

vue3+ts明日头条项目：https://github.com/wuyuanwuhui99/vue3-ts-toutiao-app-ui

在线音乐后端项目：https://github.com/wuyuanwuhui99/koa2-music-app-service
================================手机UI项目================================