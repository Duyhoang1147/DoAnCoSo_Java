package com.example.DACS.Service;

import com.example.DACS.Model.OrderInvoice;
import com.example.DACS.Repository.OrderInvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderInvoiceService {

    private OrderInvoiceRepository orderInvoiceRepository;

    public List<OrderInvoice> getAllOrderInvoice() {
        return orderInvoiceRepository.findAll();
    }

    public void addOrderInvoice(OrderInvoice orderInvoice) {
        orderInvoiceRepository.save(orderInvoice);
    }

    public Optional<OrderInvoice> findOrderInvoiceById(String id) {
        return orderInvoiceRepository.findById(id);
    }

    public void updateOrderInvoice(String id, OrderInvoice orderInvoice) {
    }

    public void deleteOrderInvoice(String id) {
        if (!orderInvoiceRepository.existsById(id))
            throw new IllegalStateException("Invoice with ID " + id + " does not exist.");
        orderInvoiceRepository.deleteById(id);
    }
}
