package com.ehb.rental.rentalplatform.controller;


// Importing the necessary classes
import com.ehb.rental.rentalplatform.model.CartItem;
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
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

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
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        // Check if the cart is empty or null
        if (cart == null || cart.isEmpty()) {
            // If the cart is empty, send a message back to the view
            model.addAttribute("message", "Your cart is empty!");
            return "checkout";
        }

        // Create a new Order object
        Order order = new Order();

        // Assign the list of products from the cart to the order
        List<Product> products =
                cart.stream()
                        .map(CartItem::getProduct)
                                .toList();
        order.setProducts(products);

        //Date
        LocalDate startDate = cart.get(0).getStartDate();
        LocalDate endDate = cart.get(0).getEndDate();

        order.setStartDate(startDate);
        order.setEndDate(endDate);

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
    // ===========================================================
// === STEP 3: Show all orders of the current user (GET /my-orders) ===
// ===========================================================
    @GetMapping("/my-orders")
    public String showMyOrders(Model model) {

        // Get logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email);

        // Get all orders of this user
        List<Order> orders = orderRepository.findByUser_Id(user.getId());

        // Send orders to the HTML page
        model.addAttribute("orders", orders);

        return "my-orders";
    }

}

