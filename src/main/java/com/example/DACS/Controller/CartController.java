package com.example.DACS.Controller;

import com.example.DACS.Model.Cart;
import com.example.DACS.Model.Product;
import com.example.DACS.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String cartIndex(Model model)
    {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("cartTotal", cartService.Total());
        return "cart/cart_index";
    }

    @PostMapping("/add")
    public String addTocart(@RequestParam String productId, @RequestParam int quantity)
    {
        cartService.addToCart(productId,quantity);
        return "redirect:/shop/product";
    }

    @GetMapping("/remove/{productId}")
    public String removeCart(@PathVariable String productId)
    {
        cartService.removeCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart()
    {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
