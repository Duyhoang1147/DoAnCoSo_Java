package com.example.DACS.Controller;

import com.example.DACS.Another.ErrorValuePage;
import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Service.ProductService;
import com.example.DACS.Service.SupplierSerVice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierSerVice supplierSerVice;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listSupplier(Model model)
    {
        model.addAttribute("supplier", supplierSerVice.getAllSupplier());
        return "supplier/index_supplier";
    }

    @GetMapping("/add")
    public String createSupplier(Model model)
    {
        model.addAttribute("supplier", new Supplier());
        return "supplier/add_supplier";
    }

    @PostMapping("/add")
    public String addSupllier(@Valid @ModelAttribute("supplier") Supplier supplier,
                                 BindingResult result,
                                 Model model)
    {
        if(result.hasErrors())
        {
            model.addAttribute("supplier", supplier);
            return "supplier/add_supplier";
        }
        supplier.setProduct(supplier.getProduct());
        supplierSerVice.addSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(@PathVariable("id") String id, Model model)
    {
        Supplier supplier = supplierSerVice.findSupplierById(id).isPresent()
                ? supplierSerVice.findSupplierById(id).get() : null;
        if(supplier == null)
        {
            return ErrorValuePage.NOTFOUND.value + "Category with id " + id + " not found";
        }
        model.addAttribute("supplier", supplier);

        return "supplier/edit_supplier";
    }

    @PostMapping("/edit/{id}")
    public String UpdateSupplier(@PathVariable("id") String id,
                         @Valid @ModelAttribute("supplier") Supplier supplier,
                         BindingResult result,
                         Model model)
    {
        if(result.hasErrors())
        {
            supplier.setId(id);
            model.addAttribute("supplier", supplier);
            return "supplier/edit_supplier";
        }
        supplierSerVice.updateSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") String id,
                                 Model model)
    {
        Supplier supplier = supplierSerVice.findSupplierById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        supplierSerVice.deleteSupplier(id);
        model.addAttribute("supplier", supplierSerVice.getAllSupplier());
        return "redirect:/suppliers";
    }
}
