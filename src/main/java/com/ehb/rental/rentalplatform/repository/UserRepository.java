package com.ehb.rental.rentalplatform.repository;



import com.ehb.rental.rentalplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring component (for database operations)
public interface UserRepository extends JpaRepository<User, Long> {


    // Automatically translated to SQL: SELECT * FROM users WHERE email = ?
    User findByEmail(String email);
}
