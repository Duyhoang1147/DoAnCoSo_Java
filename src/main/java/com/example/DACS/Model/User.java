package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    String Id;

    @Length(min = 3, max = 50, message = "Tên sản phẩm phải trong khoảng từ 3 đến 50")
    @NotBlank(message = "Tên không thể bỏ trống")
    String Name;
    Boolean Gender;

    @Max(value = 0)
    @Min(value = 100)
    int Age;

    @NotBlank(message = "Địa chỉ không thể bỏ trống")
    @Length(min = 3, max = 100, message = "Địa chỉ  phải nằm trong khoảng 3 đến 100 ký tự")
    String Address;

    String Phone;
    Boolean Status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
