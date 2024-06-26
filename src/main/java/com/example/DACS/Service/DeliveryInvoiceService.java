package com.example.DACS.Service;

import com.example.DACS.Model.DeliveryInvoice;
import com.example.DACS.Repository.DeliveryInvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class DeliveryInvoiceService {
    private DeliveryInvoiceRepository deliveryInvoiceRepository;

    public List<DeliveryInvoice> getAllDeliveryInvoices(){
        return deliveryInvoiceRepository.findAll();
    }
    public void addDeliveryInvoice(DeliveryInvoice deliveryInvoice){
        deliveryInvoiceRepository.save(deliveryInvoice);
    }
    public Optional<DeliveryInvoice> findDeliveryInvoiceById(String id){
        return deliveryInvoiceRepository.findById(id);
    }

    public void modifyDeliveryInvoice(String id, DeliveryInvoice deliveryInvoice){

    }
    public void deleteDeliveryInvoice(String id){

    }
}
