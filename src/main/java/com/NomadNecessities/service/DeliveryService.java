package com.NomadNecessities.service;

import com.NomadNecessities.model.Order;
import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.DeliveryRepository;
import com.NomadNecessities.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

  @Autowired private DeliveryRepository deliveryRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private NotificationService notificationService;

  public Delivery assignDelivery(Order order, User deliveryPerson) {
    Delivery delivery = new Delivery();
    delivery.setOrder(order);
    delivery.setDeliveryPerson(deliveryPerson);
    delivery.setDeliveryStatus("ASSIGNED");
    delivery.setCity(order.getCity());
    delivery.setAssignedAt(LocalDateTime.now());

    // Notify customers about the delivery person
    notificationService.notifyCustomers(order.getCity(), deliveryPerson);

    return deliveryRepository.save(delivery);
  }

  public List<Delivery> getDeliveriesByCity(String city) {
    return deliveryRepository.findByCity(city);
  }
}
