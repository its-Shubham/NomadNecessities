package com.NomadNecessities.repository;

import com.NomadNecessities.model.Notification;
import com.NomadNecessities.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUser(User user);
}
