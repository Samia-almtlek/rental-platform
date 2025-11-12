package com.ehb.rental.rentalplatform.repository;


import com.ehb.rental.rentalplatform.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


}

