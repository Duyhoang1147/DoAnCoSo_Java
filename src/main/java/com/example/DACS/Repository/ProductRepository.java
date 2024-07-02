package com.example.DACS.Repository;

import com.example.DACS.Model.Category;
import com.example.DACS.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByStatus(Boolean status);
    List<Product> findAllByCategory(Category category);
}
