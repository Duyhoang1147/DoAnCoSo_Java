package com.example.DACS.Model;

import jakarta.persistence.*;
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
@Table(name = "categories")
public class Category {
    @Id
    private String Id;

    @NotBlank(message = "Không thể bỏ trồng tên")
    @Length(min = 3, max = 100, message = "tên chỉ được dài từ 3 đến 100 ký tự")
    private String Name;
}
