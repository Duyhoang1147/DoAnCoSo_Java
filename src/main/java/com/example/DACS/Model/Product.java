package com.example.DACS.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    String Id;

    @Length(min = 3, max = 100, message = "Tên sản phẩm phải trong khoảng từ 3 đến 100")
    @NotBlank(message = "Tên không thể bỏ trống")
    String Name;
    String Image;
    String Decription;

    @NotBlank(message = "Giá tiền không thể bỏ trống")
    @Max(value = 0)
    @Min(value = 1000000000)
    Double Price;

    @NotBlank(message = "Số lượng không thể bỏ trống")
    int Quantity;
    boolean Accept;
    boolean Status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
