package com.ehb.rental.rentalplatform.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Start and end dates of the rental period
    private LocalDate startDate;
    private LocalDate endDate;


    private String status;

    //  Relationship with User
    // Many orders can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //  Relationship with Products
    // One order can include many products
    // One product can also appear in many orders
    // So this is a Many-to-Many relationship
    @ManyToMany
    @JoinTable(
            name = "order_products", // Name of the link table
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    //  Constructors
    public Order() {}

    public Order(LocalDate startDate, LocalDate endDate, String status, User user, List<Product> products) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
        this.products = products;
    }

    //  Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
