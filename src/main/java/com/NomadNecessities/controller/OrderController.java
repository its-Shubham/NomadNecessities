package com.NomadNecessities.controller;

import com.NomadNecessities.model.Order;
import com.NomadNecessities.model.Payment;
import com.NomadNecessities.model.User;
import com.NomadNecessities.service.OrderService;
import com.NomadNecessities.service.PaymentService;
import com.NomadNecessities.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired private OrderService orderService;

  @Autowired private PaymentService paymentService;

  @Autowired private UserService userService;

  @PostMapping
  public ResponseEntity<Order> placeOrder(@RequestBody Order order, @RequestParam Long paymentId) {
    // Retrieve the payment using the paymentId
    Payment payment =
        paymentService.getPaymentById(paymentId); // Create this method in PaymentService
    order = orderService.placeOrder(order, payment);
    return ResponseEntity.ok(order);
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
    User customer = userService.findById(customerId); // Create this method in UserService
    List<Order> orders = orderService.getOrdersByCustomer(customer);
    return ResponseEntity.ok(orders);
  }
}
