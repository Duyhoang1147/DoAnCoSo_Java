package com.example.DACS.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String Id;

    @Length(min = 3, max = 50, message = "Tên sản phẩm phải trong khoảng từ 3 đến 50")
    @NotBlank(message = "Tên không thể bỏ trống")
    private String Name;


}
