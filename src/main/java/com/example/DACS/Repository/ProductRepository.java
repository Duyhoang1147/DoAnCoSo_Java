package com.example.DACS.Repository;

import com.example.DACS.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    long countByCategoryId(String categoryId);
    List<Product> findAllByStatus(boolean status);
}
