package com.ehb.rental.rentalplatform.controller;

import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    // Show products in the cart
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        model.addAttribute("cart", cart);
        return "cart";
    }

    // add an item for the  cart
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }
            cart.add(product);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    // delete an item from the cart
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(p -> p.getId().equals(id));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }
}
