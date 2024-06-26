package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "product")
public class Product {
    @Id
    private String Id;

    @Length(min = 3, max = 100, message = "Tên sản phẩm phải trong khoảng từ 3 đến 100")
    @NotBlank(message = "Tên không thể bỏ trống")
    private String Name;
    private String Image;
    private String Decription;

    @NotBlank(message = "Giá tiền không thể bỏ trống")
    @Max(value = 0)
    @Min(value = 1000000000)
    private Double Price;

    @NotBlank(message = "Số lượng không thể bỏ trống")
    private int Quantity;
    private boolean Accept;
    private boolean Status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "product")
    private List<DeliveryInvoice> deliveryinvoice;

    @ManyToMany(mappedBy = "product")
    private List<OrderInvoice> orderInvoice;

    @ManyToMany(mappedBy = "product")
    private List<Supplier> supplier;
}
