package com.example.DACS.Service;

import com.example.DACS.Model.Cart;
import com.example.DACS.Model.OrderDetail;
import com.example.DACS.Model.OrderInvoice;
import com.example.DACS.Model.User;
import com.example.DACS.Repository.OrderDetailRepository;
import com.example.DACS.Repository.OrderInvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderInvoiceService {
    @Autowired
    private OrderInvoiceRepository orderInvoiceRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private RandomIdService randomIdService;

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

    public OrderInvoice creatorder(List<Cart> cartItem)
    {
        OrderInvoice order = new OrderInvoice();
        User user = userService.getCurrentUser();
        order.setId(randomIdService.generateId());
        order.setUser(user);
        order = orderInvoiceRepository.save(order);

        for(Cart cart : cartItem)
        {
            OrderDetail detail = new OrderDetail();
            detail.setId(randomIdService.generateId());
            detail.setOrderInvoice(order);
            detail.setProduct(cart.getProduct());
            detail.setQuantity(cart.getQuantity());
            orderDetailRepository.save(detail);
        }
        cartService.clearCart();
        return order;
    }
}
