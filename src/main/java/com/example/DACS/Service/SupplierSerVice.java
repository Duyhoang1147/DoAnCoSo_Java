package com.example.DACS.Service;

import com.example.DACS.Model.Category;
import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Repository.ProductRepository;
import com.example.DACS.Repository.SupplierRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
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

    public void addSupplier(Supplier supplier){ supplierRepository.save(supplier); }

    public Optional<Supplier> findSupplierById(String id){
        return supplierRepository.findById(id);
    }

    public void updateSupplier(@NotNull Supplier supplier)
    {
        Supplier updateSupplier = supplierRepository.findById(supplier.getId())
                .orElseThrow(() -> new IllegalStateException("Supplier with " + supplier.getId() + "dose not exist"));
        updateSupplier.setName(supplier.getName());
        updateSupplier.setAddress((supplier.getAddress()));
        updateSupplier.setPhone(supplier.getPhone());
        updateSupplier.setStatus(supplier.isStatus());
        supplierRepository.save(updateSupplier);
    }

    public void deleteSupplier(@NotNull Supplier supplier)
    {
        Supplier updateSupplier = supplierRepository.findById(supplier.getId())
                .orElseThrow(() -> new IllegalStateException("Supplier with " + supplier.getId() + "dose not exist"));
        updateSupplier.setStatus(supplier.isStatus());
        supplierRepository.save(supplier);
    }
}
