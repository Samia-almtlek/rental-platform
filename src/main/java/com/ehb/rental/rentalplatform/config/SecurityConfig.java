package com.ehb.rental.rentalplatform.config;

import com.ehb.rental.rentalplatform.repository.UserRepository;
import com.ehb.rental.rentalplatform.config.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Using BCrypt to hash passwords securely
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Main Spring Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        // Public URLs that do not require authentication
                        .requestMatchers("/register", "/login", "/h2-console/**", "/css/**", "/js/**").permitAll()
                        // Every other request requires login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Custom login page URL
                        .loginPage("/login")
                        // Telling Spring Security that the username field is "email"
                        .usernameParameter("email")
                        .passwordParameter("password")
                        // Redirect user after successful login
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        // Logout URL
                        .logoutUrl("/logout")
                        // Redirect after logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                // Disable CSRF (needed for H2 console)
                .csrf(csrf -> csrf.disable())
                // Allow using H2 console in frames
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}