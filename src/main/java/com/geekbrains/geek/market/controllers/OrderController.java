package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping(produces = "aplication/json")
    public List<OrderDto> showOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orders = orderService.findByUsers(user);
        List<OrderDto> orderDtos = orders.stream().map(OrderDto:: new).collect(Collectors.toList());
        return orderDtos;

    }

    @PostMapping("/create")
    public void makeOrder(Principal principal, @RequestParam(name= "phone") int phone, @RequestParam(name = "address") String address) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address,phone);
        orderService.save(order);
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "receiver_name") String receiverName,
                              @RequestParam(name = "phone_number") String phone
                              ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address, phone);
        order = orderService.save(order);
        return "Ваш заказ #" + order.getId();
    }
}
