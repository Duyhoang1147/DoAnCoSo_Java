package com.example.DACS.Service;

import com.example.DACS.Model.Product;
import com.example.DACS.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public Optional<Product> findProductById(String id){
        return productRepository.findById(id);
    }

    public void modifyProduct(String id, Product product){

    }
    public void deleteProduct(String id){

    }
}
