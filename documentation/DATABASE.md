
# Database Documentation 

This application uses an in-memory **H2 database** for development and demonstration purposes.  
The schema is generated automatically by JPA, and sample data is inserted using `data.sql`.



## 1. Database Configuration

Defined in `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:rentaldb
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
````

**Purpose:**

* Lightweight database for fast testing
* No installation required
* Automatic schema creation from JPA entities



## 2. Schema Overview (Generated Automatically)

Tables created:

* **users**
* **categories**
* **products**
* **orders**
* **order_products** (join table)



## 3. Relationships (ERD Summary)

* Category → Products: **One-to-Many**
* User → Orders: **One-to-Many**
* Order ↔ Products: **Many-to-Many**


## 4. Initial Data (data.sql)

The catalog is preloaded with:

* **3 categories**
* **6 sample products**

This ensures the UI has items to display immediately.



## Summary

The H2 database provides a simple, fast, and automatic setup suitable for the required proof-of-concept.
JPA handles table creation, and `data.sql` provides sample data to support catalog, cart, and checkout flows.

```

