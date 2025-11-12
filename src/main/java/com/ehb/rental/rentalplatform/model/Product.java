package com.ehb.rental.rentalplatform.model;



import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    private String description;


    private boolean available;

    // 👇 Relationship with Category
    // Many products can belong to one category
    // 'JoinColumn' will create a 'category_id' column in the 'products' table
    // This side (Product) owns the relationship
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 👇 Constructors
    public Product() {}

    public Product(String name, String description, boolean available, Category category) {
        this.name = name;
        this.description = description;
        this.available = available;
        this.category = category;
    }

    // 👇 Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

