package com.ehb.rental.rentalplatform.controller;


// Importing the necessary classes
import com.ehb.rental.rentalplatform.model.Order;
import com.ehb.rental.rentalplatform.model.Product;
import com.ehb.rental.rentalplatform.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ehb.rental.rentalplatform.model.User;
import com.ehb.rental.rentalplatform.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;


@Controller  // Marks this class as a Spring MVC Controller (it handles web requests)
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired  // Automatically injects the OrderRepository instance
    private OrderRepository orderRepository;

    // ===========================================================
    // === STEP 1: Show the checkout page (GET /checkout) ===
    // ===========================================================
    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, Model model) {

        // Retrieve the "cart" object from the user's session
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        // Add the cart to the model so it can be displayed in the view (checkout.html)
        model.addAttribute("cart", cart);

        // Return the name of the HTML file that should be displayed
        return "checkout";
    }

    // ===========================================================
    // === STEP 2: Confirm and save the order (POST /checkout/confirm) ===
    // ===========================================================
    @PostMapping("/checkout/confirm")
    public String confirmOrder(HttpSession session, Model model) {

        // Retrieve the cart items again from the session
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        // Check if the cart is empty or null
        if (cart == null || cart.isEmpty()) {
            // If the cart is empty, send a message back to the view
            model.addAttribute("message", "Your cart is empty!");
            return "checkout";
        }

        // Create a new Order object
        Order order = new Order();

        // Assign the list of products from the cart to the order
        order.setProducts(cart);

        // Set the starting date of the order (today)
        order.setStartDate(LocalDate.now());

        // Set the ending date (example: 7 days later)
        order.setEndDate(LocalDate.now().plusDays(7));

        // Set the status of the order to "Confirmed"
        order.setStatus("Confirmed");

        // ✅ Get current user from Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email);
        order.setUser(user);

        // Save the order to the database using the repository
        orderRepository.save(order);

        // Clear the cart from the session (cart is now empty after checkout)
        session.removeAttribute("cart");

        // Add the saved order to the model (to show order details on confirmation page)
        model.addAttribute("order", order);

        // Redirect the user to the confirmation page
        return "confirmation";
    }
}

