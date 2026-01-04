# **API Endpoints**

This document lists all main endpoints in the rental platform and their purpose.
The platform uses **Spring MVC + Thymeleaf**, so all endpoints return HTML views.



### ***1. Authentication***

#### **GET /login**

Displays the login page.

#### **GET /register**

Displays the user registration form.

#### **POST /register**

Creates a new user with the following validations:

* BCrypt password encryption
* Email must be unique
* Email must end with **@student.ehb.be**
* Password must match Confirm Password
* Strong password (min 8 chars, uppercase, lowercase, digit, symbol)



### ***2. Product Catalog***

#### **GET /products**

Displays all products.

**Optional query parameter:**

* `?category=ID` → filters products by category ID
* `?category=all` → shows all products



### ***3. Cart***

#### **GET /cart**

Shows cart contents stored in the session.

#### **POST /cart/add**

Adds a product to the cart.

**Parameters:**

* `productId`
* `startDate`
* `endDate`

#### **GET /cart/remove/{id}**

Removes a product from the cart.



### ***4. Checkout & Orders***

#### **GET /checkout**

Displays the checkout page.

#### **POST /checkout/confirm**

Creates a new order, saves it to the database, clears the cart, and shows the confirmation page.

#### **GET /my-orders**

Displays all orders of the logged-in user.

