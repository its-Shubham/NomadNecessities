package com.NomadNecessities.controller;

import com.NomadNecessities.model.Order;
import com.NomadNecessities.model.User;
import com.NomadNecessities.service.DeliveryService;
import com.NomadNecessities.service.OrderService;
import com.NomadNecessities.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

  @Autowired private DeliveryService deliveryService;

  @Autowired private OrderService orderService;

  @Autowired private UserService userService;

  @PostMapping
  public ResponseEntity<Delivery> assignDelivery(
      @RequestParam Long orderId, @RequestParam Long deliveryPersonId) {
    Order order = orderService.getOrderById(orderId); // Create this method in OrderService
    User deliveryPerson =
        userService.findById(deliveryPersonId); // Create this method in UserService
    Delivery delivery = deliveryService.assignDelivery(order, deliveryPerson);
    return ResponseEntity.ok(delivery);
  }

  @GetMapping("/city/{city}")
  public ResponseEntity<List<Delivery>> getDeliveriesByCity(@PathVariable String city) {
    List<Delivery> deliveries = deliveryService.getDeliveriesByCity(city);
    return ResponseEntity.ok(deliveries);
  }
}
