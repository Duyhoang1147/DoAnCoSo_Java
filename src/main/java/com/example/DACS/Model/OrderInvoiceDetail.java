package com.example.DACS.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderInvoiceDetail {
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "orderinvoice_id")
    public OrderInvoice orderinvoice;
}
