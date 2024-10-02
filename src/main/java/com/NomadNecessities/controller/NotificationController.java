package com.NomadNecessities.controller;

import com.NomadNecessities.model.Notification;
import com.NomadNecessities.model.User;
import com.NomadNecessities.service.NotificationService;
import com.NomadNecessities.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  @Autowired private NotificationService notificationService;

  @Autowired private UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long userId) {
    User user = userService.findById(userId); // Create this method in UserService
    List<Notification> notifications = notificationService.getNotificationsByUser(user);
    return ResponseEntity.ok(notifications);
  }
}
