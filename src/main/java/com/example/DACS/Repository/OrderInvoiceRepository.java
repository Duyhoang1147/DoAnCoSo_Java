package com.example.DACS.Repository;

import com.example.DACS.Model.OrderInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInvoiceRepository extends JpaRepository<OrderInvoice, String> {
    long countByProductId(String productId);
}
