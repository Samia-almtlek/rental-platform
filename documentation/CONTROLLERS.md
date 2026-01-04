

# Controllers Documentation

The Rental Platform follows a clear MVC structure.
Each controller is responsible for a specific part of the application flow, from authentication to cart management and checkout.
All controllers return Thymeleaf HTML pages and interact with repository/model layers when needed.



### ***1. AuthController***

**Path:**
`com.ehb.rental.rentalplatform.controller.AuthController`

**Purpose:**
Handles the login page.
Authentication itself is performed by Spring Security.

**Endpoints:**

| Method | URL    | Description                           |
| ------ | ------ | ------------------------------------- |
| GET    | /login | Returns the login form (`login.html`) |



### ***2. UserController***

**Path:**
`com.ehb.rental.rentalplatform.controller.UserController`

This controller manages **registration**, **validation**, and **secure password handling**.

**Features:**

* Strong password validation (uppercase + lowercase + digit + symbol)
* Confirm password check
* Prevent duplicate emails
* Restrict registration to **@student.ehb.be**
* BCrypt password hashing

**Endpoints:**

| Method | URL       | Description                       |
| ------ | --------- | --------------------------------- |
| GET    | /register | Displays registration form        |
| POST   | /register | Validates input & creates account |

**Validation Rules:**

✔ Email must end with **@student.ehb.be**
✔ Email must be unique
✔ Password and confirm password must match
✔ Password must be strong:

* Minimum 8 characters
* Contains uppercase letter
* Contains lowercase letter
* Contains number
* Contains special character



### ***3. ProductController***

**Path:**
`com.ehb.rental.rentalplatform.controller.ProductController`

**Purpose:**
Displays the product catalog and manages category-based filtering.

**Endpoints:**

| Method | URL       | Parameters         | Description                         |
| ------ | --------- | ------------------ | ----------------------------------- |
| GET    | /products | ?category=ID / all | Shows all products or filtered list |

**Features:**

* Handles full catalog & filtering in one unified endpoint
* Sends `currentCategory` to the view to highlight dropdown selection



### ***4. CartController***

**Path:**
`com.ehb.rental.rentalplatform.controller.CartController`

**Purpose:**
Implements required **session-based shopping cart** using `HttpSession`.

**Endpoints:**

| Method | URL               | Description                         |
| ------ | ----------------- | ----------------------------------- |
| GET    | /cart             | Shows current cart items            |
| POST   | /cart/add         | Adds product + rental dates to cart |
| GET    | /cart/remove/{id} | Removes product by ID               |

**Details:**

* Uses `CartItem` objects inside the session
* Dates converted into `LocalDate`
* Cart stored as `List<CartItem>`
* Not saved to DB (matches POC requirements)



### ***5. OrderController***

**Path:**
`com.ehb.rental.rentalplatform.controller.OrderController`

**Purpose:**
Handles checkout, order creation, and user order history.

**Endpoints:**

| Method | URL               | Description                             |
| ------ | ----------------- | --------------------------------------- |
| GET    | /checkout         | Displays cart items before confirming   |
| POST   | /checkout/confirm | Creates order, saves to DB, clears cart |
| GET    | /my-orders        | Lists all orders for the logged-in user |

**Checkout Flow:**

1. User views cart → `/checkout`
2. Page shows items + rental dates
3. Backend:

    * Reads all cart items
    * Extracts list of products
    * Uses the first item's dates as rental period
    * Gets logged-in user via Spring Security
    * Saves new order using `OrderRepository`
4. Cart is cleared
5. User redirected to order confirmation page

**Order Associations:**

* Many-to-One with User
* Many-to-Many with Products
* Status always set to `"Confirmed"`

