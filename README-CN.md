# CourseEvaluation

## 如何编译

1. 在 `src/main/resources` 目录下添加 `application.yml` 文件。
2. 执行 `./gradlew build` 指令获得 jar 文件。

示例：

```yml
server:
  port: 9090
spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db_course_evaluation?characterEncoding=utf-8
    username: root
    password: example_password
  jpa:
    database: MySQL
    hibernate.ddl-auto: update
    generate-ddl: true
    show-sql: true
    open-in-view: true
```

## 如何执行

```bash
java -jar build/libs/course_evaluation-0.0.1-SNAPSHOT.jar
```

## 开源协议

<a href="https://www.gnu.org/licenses/agpl-3.0.en.html">
<img src="https://www.gnu.org/graphics/agplv3-155x51.png">
</a>
