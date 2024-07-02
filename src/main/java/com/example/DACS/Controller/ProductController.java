package com.example.DACS.Controller;

import com.example.DACS.Model.Category;
import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Service.CategoryService;
import com.example.DACS.Service.ProductService;
import com.example.DACS.Service.SupplierSerVice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SupplierSerVice supplierSerVice;

    @GetMapping
    public String showForm(Model model)
    {
        model.addAttribute("product", productService.getAllProduct());
        return "product/index_product";
    }

    @GetMapping("/add")
    public String addForm(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("suppliers", supplierSerVice.getAllSupplier());
        return "product/add_product";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult error, Model model)
    {
        if(error.hasErrors())
        {
            model.addAttribute("product", product);
            return "product/add_product";
        }
        product.setImage("/Images/" + product.getImage());
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/filter")
    public String filterProduct(@RequestParam("category") String categoryId, Model model)
    {
        if(categoryId == null)
            return "redirect:/products";
        Category category = new Category();
        category.setId(categoryId);
        List<Product> filteredProduct = productService.getAllProductByCategory(category);
        model.addAttribute("filterProduct", filteredProduct);
        return "product/index_product";
    }
}
