package com.ehb.rental.rentalplatform.controller;

import com.ehb.rental.rentalplatform.model.CartItem;
import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            HttpSession session) {

        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {

            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            CartItem item = new CartItem(
                    product,
                    LocalDate.parse(startDate),
                    LocalDate.parse(endDate)
            );

            cart.add(item);

            session.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }


    // delete an item from the cart
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId().equals(productId));
            session.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }

}
