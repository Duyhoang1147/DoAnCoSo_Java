package com.example.DACS.Controller;

import com.example.DACS.Another.ErrorValuePage;
import com.example.DACS.Model.Category;
import com.example.DACS.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/index_category";
    }

    @GetMapping("/add")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add_category";
    }

    @PostMapping("/add")
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "categories/add_category";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") String id,
                                   Model model) {
        Category category = categoryService.findCategoryById(id).isPresent() ? categoryService.findCategoryById(id).get() : null;
        if (category == null) {
            return ErrorValuePage.NOTFOUND.value + "Category with id " + id + " not found";
        }
        model.addAttribute("category", category);
        return "/categories/edit_category";
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable("id") String id,
                                 @Valid @ModelAttribute("category") Category category,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            return "/categories/edit_category";
        }
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id,
                                 Model model) {
        Category category = categoryService.findCategoryById(id).isPresent() ? categoryService.findCategoryById(id).get() : null;
        if (category == null) {
            return ErrorValuePage.NOTFOUND.value + "Category with id " + id + " not found";
        }
        return "redirect:/categories";
    }
}
