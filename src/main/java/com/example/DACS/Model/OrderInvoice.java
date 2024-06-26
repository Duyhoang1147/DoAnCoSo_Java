package com.example.DACS.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
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
