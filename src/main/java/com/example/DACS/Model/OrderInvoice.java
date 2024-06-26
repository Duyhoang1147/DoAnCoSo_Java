package com.example.DACS.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class OrderInvoice {
    private String Id;
    private Date Create;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToMany
    @JoinTable(
            name = "OrderInvoice_Product",
            joinColumns = @JoinColumn(name = "orderinvoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> product;
}
