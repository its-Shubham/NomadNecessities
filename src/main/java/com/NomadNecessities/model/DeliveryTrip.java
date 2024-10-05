package com.NomadNecessities.model;

import com.NomadNecessities.constant.TripStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "delivery_trips")
public class DeliveryTrip {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_person_id", nullable = false)
  private User deliveryPerson;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "source_city_id", nullable = false)
  private City sourceCity;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TripStatus status;

  @OneToMany(mappedBy = "deliveryTrip", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Order> orders = new ArrayList<>();

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
