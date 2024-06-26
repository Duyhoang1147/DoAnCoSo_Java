package com.example.DACS.Repository;

import com.example.DACS.Model.DeliveryInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInvoiceRepository extends JpaRepository<DeliveryInvoice, String> {
}
