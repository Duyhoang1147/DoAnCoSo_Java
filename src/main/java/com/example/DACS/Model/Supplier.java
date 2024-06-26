package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class Supplier {
    @Id
    String Id;

    @NotBlank(message = "Tên không thể bỏ trống")
    @Length(min = 3, max = 50, message = "Tên phải nằm trong khoảng 3 đến 100 ký tự")
    String Name;

    @NotBlank(message = "Địa chỉ không thể bỏ trống")
    @Length(min = 3, max = 100, message = "Địa chỉ  phải nằm trong khoảng 3 đến 100 ký tự")
    String Address;
    String Phone;
    boolean Status;

    @ManyToMany
    @JoinTable(
            name = "Supplier_Product",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> product;

    @OneToMany
    @JoinColumn(name = "deliveryinvoice_id")
    public List<DeliveryInvoice> deliveryinvoice;
}
