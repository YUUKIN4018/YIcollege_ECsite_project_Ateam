package com.college.yi.ecsite.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.college.yi.ecsite.entity.Product;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public CartItem() {}

    public CartItem(Long userId, Product product, int quantity) {
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

    // Getter / Setter
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


