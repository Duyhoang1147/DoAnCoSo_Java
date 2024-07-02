package com.example.DACS.Controller;

import com.example.DACS.Model.Cart;
import com.example.DACS.Model.Product;
import com.example.DACS.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cartIndex(Model model)
    {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("cartTotal", cartService.Total());
        return "cart/cart_index";
    }
}
