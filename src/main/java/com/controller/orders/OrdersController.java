package com.controller.orders;

import com.dto.order.Order;
import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {
    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ModelAndView allOrders(){
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orders = orderService.allOrders();
        modelAndView.setViewName("ordersPage");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping(value = "/editOrder/{id}")
    public ModelAndView editOrdersPage(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOrdersPage");
        modelAndView.addObject("order", orderService.getById(id));
        return modelAndView;
    }

    @PostMapping(value = "/editOrder")
    public ModelAndView editOrder(@ModelAttribute("order") Order order, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        orderService.edit(order);
        return modelAndView;
    }

    @GetMapping(value = "/addOrder")
    public ModelAndView addOrderPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOrdersPage");
        return modelAndView;
    }

    @PostMapping(value = "/addOrder")
    public ModelAndView addOrder(@ModelAttribute("order") Order order){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        orderService.add(order);
        return modelAndView;
    }

    @GetMapping(value="/deleteOrder/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Order order = orderService.getById(id);
        orderService.delete(order);
        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
