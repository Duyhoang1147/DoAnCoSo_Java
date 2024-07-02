package com.example.DACS.Service;

import com.example.DACS.Model.Cart;
import com.example.DACS.Model.Product;
import com.example.DACS.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {

    private List<Cart> cartItem = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(String productId, int Quantity)
    {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Not found product with id: " + productId));
        cartItem.add(new Cart(product,Quantity));
    }

    public List<Cart> getCart()
    {
        return cartItem;
    }

    public void removeCart(String productId)
    {
        cartItem.removeIf(p -> p.getProduct().getId().equals(productId));
    }

    public void clearCart()
    {
        cartItem.clear();
    }

    public long countCart()
    {
        return cartItem.stream().count();
    }

    public double Total()
    {
        double Total = 0;
        for (Cart cart : cartItem)
        {
            Total += cart.getProduct().getPrice();
        }
        return Total;
    }
}
