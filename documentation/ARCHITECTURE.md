# Architecture Overview 

This project follows a simple and clear Spring Boot MVC architecture suitable for a proof-of-concept web application. The system consists of four main layers: **Model**, **Repository**, **Controller**, and **View (Thymeleaf)**.



## 1. Models (Entities)
Domain classes representing the data structure of the application:

- **User** – registered platform users
- **Product** – rentable items
- **Category** – grouping of products
- **Order** – confirmed rentals
- **CartItem** – in-memory session object (not persisted)

JPA automatically generates the database schema from these entities.



## 2. Repositories
Spring Data JPA repositories for database access:

- `UserRepository`
- `ProductRepository`
- `CategoryRepository`
- `OrderRepository`

Each repository extends `JpaRepository` for CRUD operations.



## 3. Controllers
Controllers manage the application flow:

- **AuthController** – login page
- **UserController** – registration
- **ProductController** – product catalog & filtering
- **CartController** – session-based shopping cart
- **OrderController** – checkout & order history

All controller methods return Thymeleaf HTML views.



## 4. Security Layer
Spring Security protects the application:

- BCrypt password hashing
- CustomUserDetailsService for loading users by email
- Authentication required for all protected pages (cart, checkout, orders)



## 5. Views (Thymeleaf)
HTML pages rendered from templates:

- `products.html`
- `cart.html`
- `checkout.html`
- `confirmation.html`
- `my-orders.html`
- Authentication pages (`login.html`, `register.html`)



## 6. Database
The application uses an in-memory **H2 database** with:

- Auto-generated schema (JPA)
- Initial sample data in `data.sql`



## Summary
The architecture is intentionally simple to match the proof-of-concept requirements:  
Spring MVC + Thymeleaf + H2 + Spring Security, with session-based cart and database-stored users and orders.
