# Spring Boot BlogPlatform Application

This is an application for Java Spring Boot using:
- Spring Data JPA
- PostgreSQL Database
- Thymeleaf
- Spring Security
- Model View Controller (MVC) architecture

- open http://localhost:8090 and Blog away!

## Built-in Account and Constraints

- Login as a User with `user.user@domain.com` and password `password`
- Login as an Admin with `admin.admin@domain.com` and password `password`
- An **Anonymous** account can only `READ` Posts
- A **User** account can `CREATE, READ, UPDATE` Posts
- An **Admin** account can `CREATE, READ, UPDATE, DELETE` Posts
