package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
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
    boolean status;

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
