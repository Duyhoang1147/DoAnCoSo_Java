package com.example.DACS.Service;

import com.example.DACS.Model.Category;
import com.example.DACS.Repository.CategoryRepository;
import com.example.DACS.Repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public Optional<Category> findCategoryById(String id){
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(@NotNull Category category)
    {
        Category updateCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " + category.getId() + " does not exist."));
        updateCategory.setName(category.getName());
        categoryRepository.save(updateCategory);
    }

    public void deletecategory(@NotNull Category category)
    {
        Category updateCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalStateException("Category with ID " + category.getId() + " does not exist."));
        updateCategory.setStatus(category.getStatus());
        categoryRepository.save(updateCategory);
    }
}
