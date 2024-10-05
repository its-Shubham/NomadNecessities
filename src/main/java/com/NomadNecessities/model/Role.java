package com.NomadNecessities.model;

import com.NomadNecessities.constant.RoleName;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role_name", unique = true)
  @Enumerated(EnumType.STRING)
  private RoleName roleName;
}
