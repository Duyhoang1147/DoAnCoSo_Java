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
@Table(name = "orderinvoice")
public class OrderInvoice {
    @Id
    private String Id;
    private Date Create;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToMany()
    @JoinTable(
            name = "OrderInvoice_Product",
            joinColumns = @JoinColumn(name = "orderinvoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> product;
}
