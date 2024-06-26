package com.example.DACS.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;

public class DeliveryInvoice {
    private String Id;
    private Date Create;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public Supplier supplier;

    @OneToMany
    @JoinColumn(name = "deliveryinvoicedetail_id")
    public DeliveryInvoiceDetail deliveryinvoicedetail;

}
