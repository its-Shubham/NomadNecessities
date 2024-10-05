package com.NomadNecessities.controller;

import com.NomadNecessities.dto.OrderDTO;
import com.NomadNecessities.dto.OrderPlacementDTO;
import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.model.Order;
import com.NomadNecessities.service.OrderService;
import com.NomadNecessities.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired private OrderService orderService;


  @Autowired private UserService userService;

  @PostMapping
  public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderPlacementDTO orderPlacementDTO) {
    OrderDTO createdOrder = orderService.placeOrder(orderPlacementDTO);
    return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
    UserRegistrationDTO customer =
        userService.findById(customerId); // Create this method in UserService
    List<Order> orders = orderService.getOrdersByCustomer(customer);
    return ResponseEntity.ok(orders);
  }
}
