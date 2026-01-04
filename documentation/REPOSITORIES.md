# Repository Layer Documentation

This section documents all repository interfaces used in the Rental Platform.
Repositories provide database access using **Spring Data JPA**, offering built-in CRUD operations and custom queries based on method naming conventions.

All repositories extend:

```
JpaRepository<EntityType, PrimaryKeyType>
```

This automatically provides methods such as:

* `findAll()`
* `findById()`
* `save()`
* `deleteById()`
* and more…

Custom query methods are added through Spring Data’s naming conventions.



### ***1. UserRepository***

**Path:**
`com.ehb.rental.rentalplatform.repository.UserRepository`

``` java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
```

#### **Purpose**

Handles all database operations related to the `User` entity.

#### **Custom Methods**

| Method                      | Returns | Description                                    |
| --------------------------- | ------- | ---------------------------------------------- |
| `findByEmail(String email)` | User    | Retrieves a user by their unique email address |

#### **Used In**

* Registration (checking if the email already exists)
* Login authentication (`CustomUserDetailsService`)



### ***2. ProductRepository***

**Path:**
`com.ehb.rental.rentalplatform.repository.ProductRepository`

``` java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
    List<Product> findByCategory_Id(Long categoryId);
}
```

#### **Purpose**

Provides all CRUD operations for `Product` and supports category-based filtering.

#### **Custom Methods**

| Method                       | Returns | Description                                           |
| ---------------------------- | ------- | ----------------------------------------------------- |
| `findByCategory(Category c)` | List    | Fetches all products belonging to a specific category |
| `findByCategory_Id(Long id)` | List    | Filters products using only the category ID           |

#### **Used In**

* Product catalog (`/products`)
* Filtering dropdown menu
* Category browsing



### ***3. CategoryRepository***

**Path:**
`com.ehb.rental.rentalplatform.repository.CategoryRepository`

```java
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
```

#### **Purpose**

Handles CRUD operations for `Category`.

#### **Custom Methods**

No additional methods are required (default JPA methods are sufficient).

#### **Used In**

* Sample data loading via `data.sql`
* Linking categories with products



### ***4. OrderRepository***

**Path:**
`com.ehb.rental.rentalplatform.repository.OrderRepository`

```java
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Long userId);
}
```

#### **Purpose**

Manages database interactions for `Order` (user checkout / rental orders).

#### **Custom Methods**

| Method                   | Returns | Description                                        |
| ------------------------ | ------- | -------------------------------------------------- |
| `findByUser_Id(Long id)` | List    | Returns all orders associated with a specific user |

#### **Used In**

* “My Orders” page (`/my-orders`)
* Linking orders with the authenticated user during checkout

