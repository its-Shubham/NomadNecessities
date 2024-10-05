package com.NomadNecessities.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
  private Long id;
  private Long customerId;
  private String status;
  private Double totalAmount;
  private Double deliveryCharge;
  private Long deliveryAddressId;
  private Long sourceCityId;
  private List<OrderItemDTO> items;
}
