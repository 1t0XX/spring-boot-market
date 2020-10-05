package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    Cart cart;

    @GetMapping
    public String findAll(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/order")
    public String doOrder(){
        return "order";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Order order){
        order.setCost(cart.getPrice());
        cart.getItems().forEach(io -> io.setOrder(order));
        order.setItems(cart.getItems());
        orderService.save(order);
        cart.clear();
        return "redirect:/orders";
    }
}
