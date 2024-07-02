package com.example.DACS.Controller;

import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Service.CategoryService;
import com.example.DACS.Service.ImagesService;
import com.example.DACS.Service.ProductService;
import com.example.DACS.Service.SupplierSerVice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    ImagesService imagesService;

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
    public String addProduct(@Valid Product product,
                             MultipartFile fileImage,
                             BindingResult error,
                             Model model)
    {
        try {
            if(error.hasErrors()){
                throw new IOException("Fail");
            }
            product.setImage(imagesService.saveImage(fileImage));
        }
        catch (IOException e){
            model.addAttribute("product", product);
            return "product/add_product";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id,
                                Model model) {
        try {
            // Tìm sản phẩm theo id
            Product product = productService.findProductById(id).get();

            // Xóa ảnh kèm theo sản phẩm
            imagesService.deleteImage(product.getImage());

            // Xóa sản phẩm
            productService.deleteProduct(id);
        } catch (Exception e) {
            return "product/index_product";
        }
        return "redirect:/products";
    }
}
