package com.example.DACS.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DeliveryInvoiceDetail {

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    @ManyToOne
    @JoinColumn(name = "deliveryinvoice_id")
    public DeliveryInvoice deliveryinvoice;
}
