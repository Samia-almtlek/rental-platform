package com.ehb.rental.rentalplatform.controller;

import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 🌟 Unified controller for all + category filter
    @GetMapping
    public String showProducts(
            @RequestParam(required = false, defaultValue = "all") String category,
            Model model
    ) {

        List<Product> products;

        if (category.equals("all")) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findByCategory_Id(Long.valueOf(category));
        }

        model.addAttribute("products", products);
        model.addAttribute("currentCategory", category);  // for dropdown pre-selection

        return "products";
    }
}
