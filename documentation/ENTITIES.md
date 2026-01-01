# Entities Documentation 

This document summarizes the main entities used in the Rental Platform.  
Only essential fields, relationships, and purposes are included to provide a clear overview.



## 1. Product
Represents an item that can be rented.

**Fields:** id, name, description, available, category  
**Relationship:**
- Many-to-One → Category

**Purpose:**  
Displayed in the catalog and selected by users before adding to the cart.



## 2. Category
Represents a group/type of products (e.g., lighting, cables).

**Fields:** id, name, description, products  
**Relationship:**
- One-to-Many → Products

**Purpose:**  
Enables catalog filtering and organization.



## 3. CartItem (Not Persisted)
Temporary item stored in the user session during shopping.

**Fields:** product, startDate, endDate  
**Persistence:** In-memory only (no JPA annotations)

**Purpose:**  
Simplifies the cart functionality required for the proof-of-concept.



## 4. Order
Represents a finalized rental, including rental period and selected products.

**Fields:** id, startDate, endDate, status, user, products  
**Relationships:**
- Many-to-One → User
- Many-to-Many → Products

**Purpose:**  
Stores confirmed rentals and links them to the user.



## 5. User
Represents a registered platform user.

**Fields:** id, name, email, password, role, orders  
**Relationship:**
- One-to-Many → Orders

**Purpose:**  
Used for authentication, authorization, and linking orders to individuals.

**Note:**  
Registration includes runtime validation on email format and password strength.


# Notes
- The architecture remains intentionally simple due to the proof-of-concept nature of the assignment.
- `CartItem` is stored in session memory to avoid unnecessary database complexity.
- The Many-to-Many relation in `Order` is sufficient for the required functionality.
