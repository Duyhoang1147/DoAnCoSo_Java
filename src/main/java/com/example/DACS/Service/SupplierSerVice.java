package com.example.DACS.Service;

import com.example.DACS.Model.Product;
import com.example.DACS.Model.Supplier;
import com.example.DACS.Repository.SupplierRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class SupplierSerVice {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }
    public List<Supplier> getAllSupplierByStatus(boolean status){
        return supplierRepository.findAllByStatus(status);
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
        supplierRepository.save(updateSupplier);
    }

    public void deleteSupplier(String Id) {
        if (!supplierRepository.existsById(Id))
            throw new IllegalStateException("Category with ID " + Id + " does not exist.");
        supplierRepository.deleteById(Id);
    }
}
