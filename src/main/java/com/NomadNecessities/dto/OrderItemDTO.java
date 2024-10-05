package com.NomadNecessities.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
  private Long catalogItemId;
  private String customItemName;
  private Double customItemPrice;
  private String customItemDescription;
  private int quantity;
}
