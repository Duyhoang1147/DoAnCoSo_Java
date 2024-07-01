package com.example.DACS.Controller;

import com.example.DACS.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopPageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String shopProduct(Model model)
    {
        model.addAttribute("product", productService.getAllProduct());
        return "shop/shop_product";
    }

    @GetMapping("/productgrid")
    public String shopProductGrid(Model model)
    {
        model.addAttribute("product", productService.getAllProduct());
        return "shop/shop_gridproduct";
    }
}
