package com.NomadNecessities.service;

import com.NomadNecessities.exception.OrderNotFoundException;
import com.NomadNecessities.model.Order;
import com.NomadNecessities.model.Payment;
import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.OrderRepository;
import com.NomadNecessities.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private PaymentRepository paymentRepository;

  @Autowired private NotificationService notificationService;

  @Transactional
  public Order placeOrder(Order order, Payment payment) {
    payment.setPaymentStatus("PENDING");
    payment.setPaymentDate(LocalDateTime.now());
    paymentRepository.save(payment);
    order.setTotalAmount(payment.getAmount());
    order.setStatus("PENDING");
    order.setCreatedAt(LocalDateTime.now());
    return orderRepository.save(order);
  }

  public List<Order> getOrdersByCustomer(User customer) {
    return orderRepository.findByCustomer(customer);
  }

  public Order getOrderById(Long id) {
    Optional<Order> order = orderRepository.findById(id);
    return order.orElseThrow(() -> new OrderNotFoundException(String.valueOf(id)));
  }
}
