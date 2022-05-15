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

First install mongo db 4.2 or later in your system

Second in application.properties change adminUsers's value and insert your phone number

Now you can run this application

# Featurs
First click signup then create account yourself and finaly login . Now you see the following image .

![image](https://user-images.githubusercontent.com/53397941/168492767-1c414c6e-a700-45ec-bf94-30ff7ad3d25d.png)

Now click the upload button and click the send button .

![image](https://user-images.githubusercontent.com/53397941/168492923-9b7259e9-d947-42a9-aec5-e1c24817bac1.png)

For uploading has been used requestChannel and other users will be notified (broad cast)

The Users management used requestStream as well

For downloading go to Content and click on the item you want . For downloading has been used requestStream

