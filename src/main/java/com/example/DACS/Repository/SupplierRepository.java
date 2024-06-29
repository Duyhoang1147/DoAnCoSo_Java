package com.example.DACS.Repository;

import com.example.DACS.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    List<Supplier> findAllByStatus(boolean status);
}
