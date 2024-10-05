package com.NomadNecessities.model;

import com.NomadNecessities.constant.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private User customer;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column(name = "total_amount", nullable = false)
  private Double totalAmount;

  @Column(name = "delivery_charge", nullable = false)
  private Double deliveryCharge;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_address_id", nullable = false)
  private Address deliveryAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "source_city_id", nullable = false)
  private City sourceCity;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_trip_id")
  private DeliveryTrip deliveryTrip;

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
