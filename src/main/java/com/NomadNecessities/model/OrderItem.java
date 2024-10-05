package com.NomadNecessities.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "catalog_item_id")
  private CatalogItem catalogItem;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(columnDefinition = "TEXT")
  private String customDescription;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ItemType itemType;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ItemStatus status;

  public enum ItemType {
    CATALOG,
    CUSTOM
  }

  public enum ItemStatus {
    PENDING_APPROVAL,
    APPROVED,
    REJECTED
  }
}
