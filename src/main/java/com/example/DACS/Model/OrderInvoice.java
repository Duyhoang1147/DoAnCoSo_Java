package com.example.DACS.Model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;

public class OrderInvoice {
    private String Id;
    private Date Create;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @OneToMany
    @JoinColumn(name = "orderinvoicedetail_id")
    public OrderInvoiceDetail orderinvoicedetail;

}
