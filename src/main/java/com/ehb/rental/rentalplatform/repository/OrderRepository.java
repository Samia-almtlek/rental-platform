package com.ehb.rental.rentalplatform.repository;


import com.ehb.rental.rentalplatform.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//  Basic repository to handle orders (checkout system)
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

