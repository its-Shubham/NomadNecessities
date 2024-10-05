package com.NomadNecessities.service;

import static com.NomadNecessities.constant.OrderStatus.PENDING;

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
  public Order placeOrder(Order order) {
    // Set order initial status
    order.setStatus(PENDING);

    // Save the order first to ensure it has an ID before associating it with payment
    Order savedOrder = orderRepository.save(order);

    // Create and save the payment associated with this order
    Payment payment = new Payment();
    payment.setOrder(savedOrder);
    payment.setPaymentMethod("COD");
    payment.setAmount(savedOrder.getTotalAmount().add(savedOrder.getDeliveryCharge()));
    payment.setPaymentStatus("PENDING");
    payment.setPaymentDate(LocalDateTime.now());

    paymentRepository.save(payment);

    // Update order with the correct total amount (including delivery charges) and save it again
    savedOrder.setTotalAmount(payment.getAmount());
    savedOrder.setStatus(PENDING);

    return savedOrder;
  }

  public List<Order> getOrdersByCustomer(User customer) {
    return orderRepository.findByCustomer(customer);
  }

  public Order getOrderById(Long id) {
    Optional<Order> order = orderRepository.findById(id);
    return order.orElseThrow(() -> new OrderNotFoundException(String.valueOf(id)));
  }
}
