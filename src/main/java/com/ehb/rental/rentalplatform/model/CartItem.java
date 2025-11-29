package com.ehb.rental.rentalplatform.model;

import java.time.LocalDate;

public class CartItem {

    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;

    public CartItem(Product product, LocalDate startDate, LocalDate endDate) {
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Product getProduct() { return product; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public void setProduct(Product product) { this.product = product; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}
