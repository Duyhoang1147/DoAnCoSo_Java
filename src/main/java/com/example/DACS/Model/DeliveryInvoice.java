package com.example.DACS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliveryinvoice")
public class DeliveryInvoice {
    @Id
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
