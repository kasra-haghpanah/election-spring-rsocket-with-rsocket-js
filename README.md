# election-spring-rsocket-with-rsocket-js

# Notice

This application only works on Spring 2.5 or older to upload , so in pom.xml change the spring's version to 2.5.0

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.0</version>
        <relativePath/>
</parent>
```

# Technologies
- [x] [Spring Rsocket](https://github.com/benwilcock/spring-rsocket-demo)
- [x] [Spring Webflux](https://github.com/spring-projects/spring-framework/tree/main/spring-webflux)
- [x] [Spring Security](https://github.com/spring-projects/spring-security)
- [x] [Spring Rsocket Security](https://github.com/spring-tips/rsocket-security)
- [x] [Spring Data Mongodb Reactive](https://github.com/spring-projects/spring-data-mongodb/blob/main/src/main/asciidoc/reference/reactive-mongo-repositories.adoc)
- [x] [Spring Swagger](https://github.com/springfox/springfox)
- [x] [Spring Retry](https://github.com/spring-projects/spring-retry)
- [x] [Spring thymeleaf](https://github.com/thymeleaf/thymeleaf-spring)
- [x] [JWT in Webflux & Rsocket](https://github.com/jwtk/jjwt)
- [x] [Reactor Netty](https://github.com/spring-projects/spring-boot/tree/main/spring-boot-project/spring-boot-starters/spring-boot-starter-reactor-netty)
- [x] [AngularJs](https://github.com/angular/angular.js?)
- [x] [rsocket-js browserified](https://github.com/rsocket/rsocket-js/tree/master)
- [x] [bootstrap](https://github.com/twbs)


# Prerequisites

First install mongo db 4.2 or later in your system

Second in the application.properties change adminUsers's value and insert your phone number

Now you can run this application

# Application URL
http://localhost:8095

# Swagger URL
http://localhost:8095/swagger-ui/

# Featurs
First click The Signup button then create a account yourself and finaly login . Now you see the following image .

![image](https://user-images.githubusercontent.com/53397941/168492767-1c414c6e-a700-45ec-bf94-30ff7ad3d25d.png)

Now click the Upload tap then click the send button .

![image](https://user-images.githubusercontent.com/53397941/168492923-9b7259e9-d947-42a9-aec5-e1c24817bac1.png)

For uploading has been used requestChannel and other users will be notified as well (broad cast)

The Users management's tree uses requestStream as well

For downloading go to The Content tap and click on the item you want . For downloading has been used requestStream


