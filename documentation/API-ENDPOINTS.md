# API Endpoints 

This document lists all main endpoints in the rental platform and their purpose.  
The application uses Spring MVC with Thymeleaf templates, so all endpoints return HTML views.



## 1. Authentication

### GET /login
Displays the login page.

### GET /register
Displays the user registration form.

### POST /register
Creates a new user with:

- BCrypt password encryption

- Email uniqueness check

- Email must end with @student.ehb.be

- Password + Confirm Password

- Strong password validation (min 8 chars, upper, lower, digit, special char)


## 2. Product Catalog

### GET /products
Displays all products.

**Optional query parameter:**
- `?category=ID` → filters products by category
- `?category=all` → shows all products



## 3. Cart

### GET /cart
Shows cart contents stored in the session.

### POST /cart/add
Adds a product to the cart.

Parameters:
- `productId`
- `startDate`
- `endDate`

### GET /cart/remove/{id}
Removes a product from the cart.



## 4. Checkout & Orders

### GET /checkout
Displays the checkout page with cart details.

### POST /checkout/confirm
Creates an order, saves it to the database, clears the cart, and shows the confirmation page.

### GET /my-orders
Displays all orders of the logged-in user.

