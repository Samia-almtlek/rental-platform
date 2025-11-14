package com.ehb.rental.rentalplatform.controller;



import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // ➊ tells Spring that this class handles web requests
@RequestMapping("/products") // ➋ base URL for all product-related pages
public class ProductController {

    @Autowired // ➌ Spring will automatically inject (connect) the ProductRepository object
    private ProductRepository productRepository;

    // ➍ Show all products in catalog
    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productRepository.findAll(); // gets all products from DB
        model.addAttribute("products", products); // passes products to the view (HTML)
        return "products"; // name of the HTML template to display
    }

    // ➎ Show products by category
    @GetMapping("/category/{id}")
    public String showByCategory(@PathVariable("id") Long categoryId, Model model) {
        List<Product> products = productRepository.findByCategory_Id(categoryId); // custom query
        model.addAttribute("products", products);
        return "products";
    }
}

