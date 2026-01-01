# Controllers Documentation 

This project uses several Spring MVC controllers to handle the main application workflow.  
Each controller is responsible for specific functionality such as authentication, product display, cart management, and checkout.



##  AuthController
Handles the login page.

- `GET /login` → Returns the login view.

Purpose: Provides the entry point for user authentication (actual login is handled by Spring Security).



##  UserController
Manages user registration with secure password hashing.

- `GET /register` → Shows registration form
- `POST /register` → Saves new user with encrypted password and default role `"USER"`

Purpose:

- Allows new users to register securely

- Validates email format (@student.ehb.be only)

- Prevents duplicate emails

- Confirms password match

- Enforces strong password rules

- Encrypts passwords using BCrypt


##  ProductController
Displays product catalog and category filtering.

- `GET /products`
    - Shows all products or filters by category
    - Sends product list to `products.html`

Purpose: Implements the required catalog and filtering functionality.



##  CartController
Manages a session-based shopping cart (not stored in the database).

- `GET /cart` → Shows cart items
- `POST /cart/add` → Adds item with rental dates
- `GET /cart/remove/{id}` → Removes item by product ID

Purpose: Provides required cart functionality for the proof-of-concept assignment.



##  OrderController
Handles checkout and order creation.

- `GET /checkout` → Displays cart items
- `POST /checkout/confirm`
    - Creates an order
    - Assigns products and rental dates
    - Saves the order for the logged-in user
    - Clears the cart
- `GET /my-orders` → Shows the user's order history

Purpose: Implements the complete checkout flow from cart → order.


