package com.NomadNecessities.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "catalog_items")
public class CatalogItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "source_city_id", nullable = false)
  private City sourceCity;
}
