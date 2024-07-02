package com.example.DACS.Controller;

import com.example.DACS.Model.OrderDetail;
import com.example.DACS.Service.OrderDetailService;
import com.example.DACS.Service.ProductService;
import com.example.DACS.Service.OrderInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderInvoiceService orderInvoiceService;

    @GetMapping
    public String listOrderDetails(Model model) {
        model.addAttribute("orderDetails", orderDetailService.getAllOrderDetails());
        return "orderdetail/index_orderdetail";
    }

    @GetMapping("/add")
    public String showAddOrderDetailForm(Model model) {
        model.addAttribute("orderDetail", new OrderDetail());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("orderInvoices", orderInvoiceService.getAllOrderInvoice());
        return "orderdetail/add_orderdetail";
    }

    @PostMapping("/add")
    public String addOrderDetail(@Valid OrderDetail orderDetail,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderDetail", orderDetail);
            model.addAttribute("products", productService.getAllProduct());
            model.addAttribute("orderInvoices", orderInvoiceService.getAllOrderInvoice());
            return "orderdetail/add_orderdetail";
        }
        orderDetailService.addOrderDetail(orderDetail);
        return "redirect:/orderdetails";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderDetailForm(@PathVariable("id") String id, Model model) {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("orderInvoices", orderInvoiceService.getAllOrderInvoice());
        return "orderdetail/edit_orderdetail";
    }

    @PostMapping("/edit/{id}")
    public String editOrderDetail(@PathVariable("id") String id,
                                  @Valid OrderDetail orderDetail,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderDetail", orderDetail);
            model.addAttribute("products", productService.getAllProduct());
            model.addAttribute("orderInvoices", orderInvoiceService.getAllOrderInvoice());
            return "orderdetail/edit_orderdetail";
        }
        orderDetail.setId(id);
        orderDetailService.updateOrderDetail(orderDetail);
        return "redirect:/orderdetails";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderDetail(@PathVariable("id") String id) {
        orderDetailService.deleteOrderDetail(id);
        return "redirect:/orderdetails";
    }
}


