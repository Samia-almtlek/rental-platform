# Entities Documentation

This section documents all data models used in the Rental Platform.
These entities define the database schema (via JPA) and the domain logic used throughout the application.



### ***1. User Entity***

**Path:**
`com.ehb.rental.rentalplatform.model.User`

Represents a registered platform user.
Each user can have multiple orders.

**Table:** `users`

#### **Fields**

| Field    | Type   | Description                        |
| -------- | ------ | ---------------------------------- |
| id       | Long   | Primary key                        |
| name     | String | Full name of the user              |
| email    | String | Unique login identifier            |
| password | String | BCrypt-encrypted password          |
| role     | String | Security role (USER, future ADMIN) |

#### **Relationships**

* **One-to-Many** (User → Orders)

  ```
  @OneToMany(mappedBy = "user")
  private List<Order> orders;
  ```

#### **Purpose**

Used for:

* Authentication & login
* Authorization (role-based security)
* Linking orders to the logged-in user



### ***2. Product Entity***

**Path:**
`com.ehb.rental.rentalplatform.model.Product`

Represents a rentable item in the product catalog.

**Table:** `products`

#### **Fields**

| Field       | Type     | Description             |
| ----------- | -------- | ----------------------- |
| id          | Long     | Primary key             |
| name        | String   | Product name            |
| description | String   | Product details         |
| available   | boolean  | Availability status     |
| category    | Category | Category of the product |

#### **Relationships**

* **Many-to-One** (Product → Category)

  ```
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  ```

#### **Purpose**

Displayed in the catalog, can be filtered by category, added to cart, and included in orders.



### ***3. Category Entity***

**Path:**
`com.ehb.rental.rentalplatform.model.Category`

Represents a category of products (Lighting, Cables, etc.).

**Table:** `categories`

#### **Fields**

| Field       | Type   | Description               |
| ----------- | ------ | ------------------------- |
| id          | Long   | Primary key               |
| name        | String | Category name             |
| description | String | Summary                   |
| products    | List   | Products in this category |

#### **Relationships**

* **One-to-Many** (Category → Products)

  ```
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<Product> products;
  ```

#### **Purpose**

Used to structure and filter the catalog by product type.



### ***4. Order Entity***

**Path:**
`com.ehb.rental.rentalplatform.model.Order`

Represents a confirmed rental order with dates and multiple products.

**Table:** `orders`

#### **Fields**

| Field     | Type      | Description        |
| --------- | --------- | ------------------ |
| id        | Long      | Primary key        |
| startDate | LocalDate | Rental start date  |
| endDate   | LocalDate | Rental end date    |
| status    | String    | Order status       |
| user      | User      | Owner of the order |
| products  | List      | Rented products    |

#### **Relationships**

* **Many-to-One** (Order → User)

  ```
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  ```

* **Many-to-Many** (Order ↔ Product)

  ```
  @ManyToMany
  @JoinTable(
      name = "order_products",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<Product> products;
  ```

#### **Purpose**

Stores all confirmed rentals so the system can:

* Generate order confirmation
* Track rental history
* Attach orders to the logged-in user



### ***5. CartItem (Not Persisted)***

**Path:**
`com.ehb.rental.rentalplatform.model.CartItem`

Used only during the shopping process.
Stored in the session and **not** in the database.

**Table:** *Not stored* (no JPA annotations)

#### **Fields**

| Field     | Type      | Description       |
| --------- | --------- | ----------------- |
| product   | Product   | Selected product  |
| startDate | LocalDate | Rental start date |
| endDate   | LocalDate | Rental end date   |

#### **Purpose**

Represents one item in the shopping cart before confirmation.
Simplifies the checkout flow without needing database persistence.

