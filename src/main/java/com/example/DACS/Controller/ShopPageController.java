package com.example.DACS.Controller;

import com.example.DACS.Model.Product;
import com.example.DACS.Service.ProductService;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/product/{id}")
    public String  shopDetail(@PathVariable("id") String id, Model model)
    {
        Product product = productService.findProductById(id).
                orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));
        model.addAttribute("product", product);
        return "shop/shop_detail";
    }
}
