package com.NomadNecessities.model;

import com.NomadNecessities.constant.PaymentMethod;
import com.NomadNecessities.constant.PaymentStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "payments")
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  @Column(name = "transaction_id", unique = true)
  private String transactionId;

  @Column(name = "payment_date")
  private LocalDateTime paymentDate;
}
