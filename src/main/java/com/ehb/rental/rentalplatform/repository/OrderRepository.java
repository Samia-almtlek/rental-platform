package com.ehb.rental.rentalplatform.repository;


import com.ehb.rental.rentalplatform.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//  Basic repository to handle orders (checkout system)
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Long userId);

}

