package com.NomadNecessities.service;

import com.NomadNecessities.constant.NotificationStatus;
import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.model.Notification;
import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.NotificationRepository;
import com.NomadNecessities.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  @Autowired private NotificationRepository notificationRepository;

  @Autowired private UserRepository userRepository;

  public void notifyCustomers(String city, User deliveryPerson) {
    List<User> customers = userRepository.findAll(); // Assuming all users are customers
    for (User customer : customers) {
      Notification notification = new Notification();
      notification.setUser(customer);
      notification.setMessage(
          "A delivery person is going to "
              + city
              + " to fetch goods. You can also place your order.");
      notification.setStatus(NotificationStatus.PENDING);
      notification.setCreatedAt(LocalDateTime.now());
      notificationRepository.save(notification);
    }
  }

  public List<Notification> getNotificationsByUser(UserRegistrationDTO user) {
    return notificationRepository.findByUser(user);
  }
}
