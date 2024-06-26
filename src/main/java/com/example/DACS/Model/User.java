package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.query.Order;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private String Id;

    @NotBlank(message = "Bắt buộc phải nhập mật khẩu")
    private String Password;

    @Length(min = 3, max = 50, message = "Tên sản phẩm phải trong khoảng từ 3 đến 50")
    @NotBlank(message = "Tên không thể bỏ trống")
    private String Name;
    private Boolean Gender;

    @Max(value = 0)
    @Min(value = 100)
    private int Age;

    @NotBlank(message = "Địa chỉ không thể bỏ trống")
    @Length(min = 3, max = 100, message = "Địa chỉ  phải nằm trong khoảng 3 đến 100 ký tự")
    private String Address;

    private String Phone;
    private Boolean Status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    @JoinColumn(name = "orderinvoice_id")
    private List<OrderInvoice> orderinvoice;
}
