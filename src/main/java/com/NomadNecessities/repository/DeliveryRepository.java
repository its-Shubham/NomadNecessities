package com.NomadNecessities.repository;

import com.NomadNecessities.model.Delivery;
import com.NomadNecessities.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
  List<Delivery> findByDeliveryPerson(User deliveryPerson);

  List<Delivery> findByCity(String city);
}
