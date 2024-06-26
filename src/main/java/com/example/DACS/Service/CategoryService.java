package com.example.DACS.Service;

import com.example.DACS.Model.Category;
import com.example.DACS.Repository.CategoryRepository;
import com.example.DACS.Repository.ProductRepository;
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

    public boolean deleteCategory(String id){
        return false;
    }
    public void modifyCategory(String id, Category category){

    }
}
