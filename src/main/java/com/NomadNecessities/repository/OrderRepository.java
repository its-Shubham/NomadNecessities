package com.NomadNecessities.repository;

import com.NomadNecessities.model.Order;
import com.NomadNecessities.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByCustomer(User customer);

  List<Order> findByCity(String city);
}
