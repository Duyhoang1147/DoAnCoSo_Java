package com.example.DACS.Controller;

import com.example.DACS.Another.ErrorValuePage;
import com.example.DACS.Model.OrderInvoice;
import com.example.DACS.Service.OrderInvoiceService;
import com.example.DACS.Service.ProductService;
import com.example.DACS.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderinvoices")
public class OrderInvoiceController {

    @Autowired
    private OrderInvoiceService orderInvoiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listOrderInvoices(Model model) {
        model.addAttribute("orderInvoices", orderInvoiceService.getAllOrderInvoice());
        return "orderinvoices/index_orderinvoice";
    }

    @GetMapping("/add")
    public String createOrderInvoiceForm(Model model) {
        OrderInvoice orderInvoice = new OrderInvoice();
        model.addAttribute("orderInvoice", orderInvoice);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("products", productService.getAllProduct());
        return "orderinvoices/add_orderinvoice";
    }

    @PostMapping("/add")
    public String saveOrderInvoice(@Valid @ModelAttribute("orderInvoice") OrderInvoice orderInvoice,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderInvoice", orderInvoice);
            model.addAttribute("users", userService.getAllUser());
            model.addAttribute("products", productService.getAllProduct());
            return "orderinvoices/add_orderinvoice";
        }
        orderInvoiceService.addOrderInvoice(orderInvoice);
        return "redirect:/orderinvoices";
    }

    @GetMapping("/edit/{orderId}")
    public String editOrderInvoiceForm(@PathVariable("orderId") String orderId,
                                       Model model) {
        OrderInvoice orderInvoice = orderInvoiceService.findOrderInvoiceById(orderId).isPresent() ? orderInvoiceService.findOrderInvoiceById(orderId).get() : null;
        if (orderInvoice == null) {
            return ErrorValuePage.NOTFOUND.value + "Order ID " + orderId + " not found";
        }
        model.addAttribute("orderInvoice", orderInvoice);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("products", productService.getAllProduct());
        return "orderinvoices/edit_orderinvoice";
    }

    @PostMapping("/edit/{orderId}")
    public String updateOrderInvoice(@PathVariable("orderId") String orderId,
                                     @Valid @ModelAttribute("orderInvoice") OrderInvoice orderInvoice,
                                     BindingResult result,
                                     Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderInvoice", orderInvoice);
            model.addAttribute("users", userService.getAllUser());
            model.addAttribute("products", productService.getAllProduct());
            return "orderinvoices/edit_orderinvoice";
        }
        orderInvoice.setId(orderId);
        orderInvoiceService.addOrderInvoice(orderInvoice);
        return "redirect:/orderinvoices";
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrderInvoice(@PathVariable("orderId") String orderId,
                                     Model model) {
        OrderInvoice orderInvoice = orderInvoiceService.findOrderInvoiceById(orderId).isPresent() ? orderInvoiceService.findOrderInvoiceById(orderId).get() : null;
        if (orderInvoice == null) {
            return ErrorValuePage.NOTFOUND.value + "Order ID " + orderId + " not found";
        }
        orderInvoiceService.deleteOrderInvoice(orderId);
        return "redirect:/orderinvoices";
    }
}
