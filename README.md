# CourseEvaluation

[简体中文](README-CN.md)

## Description

Java Spring Boot Course Design. University Course Evaluation System.

Satisfy the restful api as much as possible.

Use jpa to implement database operations.

## Client

[pyqt](https://github.com/aimerneige/CourseEvaluationClient)
[vue](https://github.com/nidbCN/CourseEvaluation/tree/master/src/frontend/course_evaluation)

## How to build

1. Add `application.yml` file with database config at `src/main/resources`.
2. Run `./gradlew build` to get a jar file.

Example:

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

## How to run

```bash
java -jar build/libs/course_evaluation-0.0.1-SNAPSHOT.jar
```

## Warning

This project is just for learning use. It does not contain any permission check, **NEVER USE IT IN A PRODUCTION ENVIRONMENT!**

## LICENSE

<a href="https://www.gnu.org/licenses/agpl-3.0.en.html">
<img src="https://www.gnu.org/graphics/agplv3-155x51.png">
</a>
