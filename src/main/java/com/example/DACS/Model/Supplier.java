package com.example.DACS.Model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

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
}
