#  Rental Platform — Spring Boot

This project is a proof-of-concept rental platform for an art school, where students can reserve and rent equipment such as lighting, cables, and control panels.
Registered users can browse a product catalog, filter by category, add items to a cart, and confirm their reservation through a checkout flow.

The project is built with **Spring Boot, Spring MVC, Spring Security, JPA, H2 Database, and Thymeleaf**.


##  **Features**

###  Product Catalog

* Display all products
* Filter by category (Lighting, Cables, Control Panels)

###  Shopping Cart

* Add items with rental dates
* Remove items
* Session-based storage (HttpSession)

###  Checkout

* Convert cart items into an Order
* Save order in the database
* Show confirmation page

###  User System

* User registration with validation
* Email must be an EhB student email (`@student.ehb.be`)
* Password confirmation + password strength validation
* Secure login using **Spring Security + BCrypt**
* Orders linked to the logged-in user

###  Database

* H2 in-memory database
* Auto-generated schema via JPA
* Preloaded sample categories + products (data.sql)



##  **Project Structure**

```
src/main/java/com/ehb/rental/rentalplatform/
│
├── controller/
├── model/
├── repository/
├── service/
├── config/
└── resources/
    ├── templates/ (Thymeleaf pages)
    ├── static/ (CSS)
    ├── application.properties
    └── data.sql
```



##  **Security**

* CustomUserDetailsService (loads users by email)
* BCryptPasswordEncoder
* Custom login page (`/login`)
* Logout endpoint
* Public pages: /login, /register, static files
* All other routes require authentication


### Registration Validation

The registration form includes multiple security checks:
* Only EhB student emails are allowed (`@student.ehb.be`)
* Duplicate emails are blocked
* Password must match confirmation field
* Strong password policy (min 8 chars, upper/lowercase, number, symbol)



##  **Database Overview**

* H2 in-memory (`jdbc:h2:mem:rentaldb`)
* Auto-create tables with JPA
* Join table `order_products` for Many-to-Many
* Preloaded sample data (6 products, 3 categories)



##  **How to Run the Project**

1. Clone the project
2. Open in IntelliJ / VSCode
3. Run the Spring Boot application
4. Open the H2 console at:

   ```
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:rentaldb
   ```
5. Visit the website:

   ```
   http://localhost:8080/products
   ```




##  **References**

Below are the official and tutorial sources used while developing this project:

###  Official Documentation

* Spring Framework Documentation
  [https://docs.spring.io/spring-framework/reference/](https://docs.spring.io/spring-framework/reference/)

* Spring Boot Documentation
  [https://docs.spring.io/spring-boot/index.html](https://docs.spring.io/spring-boot/index.html)

* Thymeleaf Documentation
  [https://www.thymeleaf.org/documentation.html](https://www.thymeleaf.org/documentation.html)

* H2 Database Documentation
  [https://h2database.com/html/main.html](https://h2database.com/html/main.html)



###  Tutorials & Articles

* Baeldung – Spring MVC Session Attributes
  [https://www.baeldung.com/spring-mvc-session-attributes](https://www.baeldung.com/spring-mvc-session-attributes)

* Spring Boot Full Course (Amigoscode)
  [https://www.youtube.com/watch?v=her_7pa0vrg](https://www.youtube.com/watch?v=her_7pa0vrg)

* Spring Security Basics (Java Brains)
  [https://www.youtube.com/watch?v=sm-8qfMWEV8](https://www.youtube.com/watch?v=sm-8qfMWEV8)

* Spring Boot + MVC Tutorial
  [https://www.youtube.com/watch?v=8SGI_XS5OPw](https://www.youtube.com/watch?v=8SGI_XS5OPw)

* Thymeleaf + Spring Boot Introduction
  [https://www.youtube.com/watch?v=vtPkZShrvXQ](https://www.youtube.com/watch?v=vtPkZShrvXQ)

* JPA + CRUD Basics
  [https://www.youtube.com/watch?v=194UZkgEcic&list=PLzhWJrmO-SPX9CwimKF2-l0e0dcLEiAI2](https://www.youtube.com/watch?v=194UZkgEcic&list=PLzhWJrmO-SPX9CwimKF2-l0e0dcLEiAI2)

* Spring Boot tutorial in Arabic
  [https://www.youtube.com/watch?v=JuzsKGn4z4U&t=88s](https://www.youtube.com/watch?v=JuzsKGn4z4U&t=88s)



###  ChatGPT

* [https://chatgpt.com/c/69334edf-6fd0-832c-b803-1ffa1dcd0692](https://chatgpt.com/c/69334edf-6fd0-832c-b803-1ffa1dcd0692)