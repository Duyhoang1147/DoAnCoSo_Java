package com.example.DACS.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class DeliveryInvoice {
    private String Id;
    private Date Create;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier supplier;

    @ManyToMany
    @JoinTable(
            name = "DeliveryInvoice_Product",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    public List<Product> product;
}
