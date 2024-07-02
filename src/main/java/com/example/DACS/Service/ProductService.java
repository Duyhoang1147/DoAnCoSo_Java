package com.example.DACS.Service;

import com.example.DACS.Model.Product;
import com.example.DACS.Repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){ return productRepository.findAll(); }
    public List<Product> getAllProductByStatus(boolean status){ return productRepository.findAllByStatus(status);}

    public void addProduct(Product product){
        product.getSupplier().forEach(supplier -> supplier.getProduct().add(product));
        productRepository.save(product);
    }

    public Optional<Product> findProductById(String id){
        return productRepository.findById(id);
    }

    public void updateProduct(@NotNull Product product)
    {
        Product updateProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalStateException("Product with " + product.getId() + " dose not exist"));
        updateProduct.setName(product.getName());
        updateProduct.setImage(product.getImage());
        updateProduct.setDecription((product.getDecription()));
        updateProduct.setPrice(product.getPrice());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setStatus(product.isStatus());
        productRepository.save(updateProduct);
    }
    public void deleteProduct(@NotNull Product product) {
        Product updateProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalStateException("Product with " + product.getId() + " dose not exist"));
        updateProduct.setStatus(product.isStatus());
        productRepository.save(updateProduct);
    }
}
