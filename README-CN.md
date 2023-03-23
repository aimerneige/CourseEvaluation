# CourseEvaluation

## 介绍

Java Spring Boot 课程设计。大学课程评课系统。

接口尽量满足了 restful api。

数据库访问使用 jpa。

## 客户端

[pyqt](https://github.com/aimerneige/CourseEvaluationClient)
[vue](https://github.com/nidbCN/CourseEvaluation/tree/master/src/frontend/course_evaluation)

## 如何编译

1. 在 `src/main/resources` 目录下添加 `application.yml` 文件填入数据库配置。
2. 执行 `./gradlew build` 指令获得 jar 文件。

示例：

```yml
server:
  port: 9090
  error:
    whitelabel:
      enabled: false
spring:
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false
  mail:
    host: smtp.gmail.com
    port: 25
    username: account@gmail.com
    password: password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db_course_evaluation?characterEncoding=utf-8
    username: root
    password: example_password
  jpa:
    database: MYSQL
    hibernate.ddl-auto: update
    generate-ddl: true
    show-sql: true
    open-in-view: true
```

## 如何执行

```bash
java -jar build/libs/course_evaluation-0.0.1-SNAPSHOT.jar
```

## 警告

这个项目仅用于学习交流，它没有实现任何鉴权功能，**永远不要把它用在生产环境里！**

## 开源协议

<a href="https://www.gnu.org/licenses/agpl-3.0.en.html">
<img src="https://www.gnu.org/graphics/agplv3-155x51.png">
</a>
