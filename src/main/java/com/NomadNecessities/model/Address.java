package com.NomadNecessities.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "country")
  private String country;
}
