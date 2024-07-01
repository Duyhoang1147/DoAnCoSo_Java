package com.example.DACS.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Giá tiền không thể bỏ trống")
    @Min(value = 0)
    @Max(value = 1000000000)
    private Double Price;

    @NotNull(message = "Số lượng không thể bỏ trống")
    @Min(value = 1)
    @Max(value = 1000000000)
    private int Quantity;

    //private boolean Accept; Khong can thiet nua
    private boolean status;

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
