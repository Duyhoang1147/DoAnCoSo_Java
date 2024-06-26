package com.example.DACS.Service;

import com.example.DACS.Model.DeliveryInvoice;
import com.example.DACS.Repository.DeliveryInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryInvoiceService {
    @Autowired
    private DeliveryInvoiceRepository deliveryInvoiceRepository;

    public List<DeliveryInvoice> getAllDeliveryInvoices(){
        return deliveryInvoiceRepository.findAll();
    }
    public void addDeliveryInvoice(DeliveryInvoice deliveryInvoice){
        deliveryInvoiceRepository.save(deliveryInvoice);
    }
}
