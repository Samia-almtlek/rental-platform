# Security Layer Documentation

The Rental Platform uses **Spring Security** to protect all private pages, authenticate users via email, and ensure that passwords are safely encrypted.
The security layer is built using two main components:

1. `CustomUserDetailsService.java`
2. `SecurityConfig.java`

Both work together to provide a secure and modern login system.



### ***1. CustomUserDetailsService***

**Path:**
`com.ehb.rental.rentalplatform.config.CustomUserDetailsService`

This service tells Spring Security **how to load users from the database** during the login process.

#### **Key Responsibilities**

* Load a user using **email instead of username**
* Throw `UsernameNotFoundException` if the user does not exist
* Convert your `User` entity → Spring Security `UserDetails`
* Provide:

    * encrypted password (BCrypt)
    * role (USER)

#### **How It Works**

``` java
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);

    if (user == null) {
        throw new UsernameNotFoundException("User not found");
    }

    return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
}
```

#### **Summary**

This class integrates your database with Spring Security and enables:

* logging in with **email**
* checking the **BCrypt-hashed** password
* applying role-based security



### ***2. SecurityConfig***

**Path:**
`com.ehb.rental.rentalplatform.config.SecurityConfig`

This class defines all the security rules of the application, including public/private pages, login behavior, and logout flow.



### ***2.1 Password Encoder (BCrypt)***

All passwords are encrypted using BCrypt before being stored in the database.

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Why BCrypt?

* Secure hashing algorithm
* Protects passwords even if the database is leaked
* Standard in modern Spring applications



### ***2.2 URL Authorization Rules***

#### **Public (no login required)**

* `/register`
* `/login`
* `/h2-console/**`
* `/css/**`
* `/js/**`

#### **Protected (login required)**

* All other routes:

    * `/products`
    * `/cart`
    * `/checkout`
    * `/my-orders`
    * and any future endpoints



### ***2.3 Login Configuration***

``` java
.formLogin(form -> form
    .loginPage("/login")
    .usernameParameter("email")
    .passwordParameter("password")
    .defaultSuccessUrl("/products", true)
)
```

#### **Features**

* Login using **email instead of username**
* Custom login page (`login.html`)
* After login → user is always redirected to `/products`

---

### ***2.4 Logout Configuration***

``` java
.logout(logout -> logout
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login?logout")
)
```

After the user logs out:

* Session is cleaned
* User is redirected to the login page



### ***2.5 Development Settings (H2 Console Support)***

To allow H2 database console to run:

``` java
.csrf(csrf -> csrf.disable())
.headers(headers -> headers.frameOptions(frame -> frame.disable()));
```

This is **safe for development only**, and required because:

* H2 console uses frames
* CSRF protection blocks form submissions


