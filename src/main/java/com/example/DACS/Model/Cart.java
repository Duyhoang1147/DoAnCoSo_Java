package com.example.DACS.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Cart {
    private Product product;
    private int Quantity;

    public Cart(Product product, int quantity)
    {
        this.product = product;
        this.Quantity = quantity;
    }
}
