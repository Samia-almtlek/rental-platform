package com.ehb.rental.rentalplatform.repository;


import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //  Custom query method to find all products belonging to a specific category
    // Translates automatically into SQL:
    // SELECT * FROM products WHERE category_id = ?;
    List<Product> findByCategory(Category category);


}
