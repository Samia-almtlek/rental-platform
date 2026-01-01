
# Security Documentation 

This project uses Spring Security to provide authentication, protect pages, and store passwords securely.

**Registration includes server-side validation for:**
- Email format (must end with @student.ehb.be)
- Unique email constraint
- Password confirmation
- Strong password rules (uppercase, lowercase, digit, special character, min length 8)


## 1. Password Encryption (BCrypt)
A `BCryptPasswordEncoder` bean is used to hash all passwords before saving them in the database.

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
````

Purpose: Ensures safe password storage and prevents plain-text passwords.



## 2. CustomUserDetailsService

The application loads users using a custom implementation of `UserDetailsService`.

* Finds users by their email
* Throws exception if not found
* Returns a Spring Security `UserDetails` object with:

    * Email
    * Encrypted password
    * User role

Purpose: Allows Spring Security to authenticate users stored in our database.



## 3. SecurityFilterChain (Main Security Rules)

`SecurityConfig` defines which URLs are public and which require authentication.

**Public pages (no login needed):**

* `/login`
* `/register`
* `/css/**`, `/js/**`
* `/h2-console/**`

**Protected pages:**

* Everything else (products, cart, checkout, orders)

**Login settings:**

* Custom login page: `/login`
* Username field changed to: `email`
* Redirect after login: `/products`

**Logout settings:**

* `/logout` → redirects to `/login?logout`

CSRF is disabled only for development (H2 console support).

