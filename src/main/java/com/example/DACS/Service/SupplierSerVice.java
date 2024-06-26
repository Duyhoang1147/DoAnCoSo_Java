package com.example.DACS.Service;

import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Repository.ProductRepository;
import com.example.DACS.Repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SupplierSerVice {
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }
    public void addSupplier(Supplier supplier){
        supplierRepository.save(supplier);
    }
    public Optional<Supplier> findSupplierById(String id){
        return supplierRepository.findById(id);
    }

    public void modifySupplier(String id, Supplier supplier){

    }
    public void deleteSupplier(String id){

    }
}
