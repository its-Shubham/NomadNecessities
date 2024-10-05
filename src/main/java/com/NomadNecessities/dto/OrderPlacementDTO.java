package com.NomadNecessities.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlacementDTO {
  private Long customerId;
  private Long sourceCityId;
  private Long deliveryAddressId;
  private List<OrderItemDTO> items;
}
