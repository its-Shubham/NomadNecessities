package com.NomadNecessities.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "goods")
public class Goods {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
}
