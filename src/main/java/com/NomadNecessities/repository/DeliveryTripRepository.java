package com.NomadNecessities.repository;

import com.NomadNecessities.model.DeliveryTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTripRepository extends JpaRepository<DeliveryTrip, Long> {}
