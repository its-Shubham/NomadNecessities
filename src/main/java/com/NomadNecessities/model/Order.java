package com.NomadNecessities.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private User customer;

  @Column(nullable = false)
  private String status; // Default: 'PENDING'

  @Column(name = "total_amount", nullable = false)
  private BigDecimal totalAmount;

  @Column(name = "delivery_charge", nullable = false)
  private BigDecimal deliveryCharge;

  @Column(nullable = false)
  private String city;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
