package com.NomadNecessities.service;


import com.NomadNecessities.constant.OrderStatus;
import com.NomadNecessities.dto.OrderDTO;
import com.NomadNecessities.dto.OrderItemDTO;
import com.NomadNecessities.dto.OrderPlacementDTO;
import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.exception.OrderNotFoundException;
import com.NomadNecessities.exception.ResourceNotFoundException;
import com.NomadNecessities.model.*;
import com.NomadNecessities.repository.*;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CatalogItemRepository catalogItemRepository;
  private final CityRepository cityRepository;
  private final AddressRepository addressRepository;
  private ModelMapper modelMapper;

  @Autowired
  public OrderService(
      OrderRepository orderRepository,
      UserRepository userRepository,
      CatalogItemRepository catalogItemRepository,
      CityRepository cityRepository,
      AddressRepository addressRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.catalogItemRepository = catalogItemRepository;
    this.cityRepository = cityRepository;
    this.addressRepository = addressRepository;
  }

  @Transactional
  public OrderDTO placeOrder(OrderPlacementDTO orderPlacementDTO) {
    User customer =
        userRepository
            .findById(orderPlacementDTO.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

    City sourceCity =
        cityRepository
            .findById(orderPlacementDTO.getSourceCityId())
            .orElseThrow(() -> new ResourceNotFoundException("Source city not found"));

    Address deliveryAddress =
        addressRepository
            .findById(orderPlacementDTO.getDeliveryAddressId())
            .orElseThrow(() -> new ResourceNotFoundException("Delivery address not found"));

    Order order = new Order();
    order.setCustomer(customer);
    order.setSourceCity(sourceCity);
    order.setDeliveryAddress(deliveryAddress);
    order.setStatus(OrderStatus.PENDING);

    List<OrderItem> orderItems = new ArrayList<>();
    double totalAmount = 0.0;

    for (OrderItemDTO itemDTO : orderPlacementDTO.getItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setQuantity(itemDTO.getQuantity());

      if (itemDTO.getCatalogItemId() != null) {
        CatalogItem catalogItem =
            catalogItemRepository
                .findById(itemDTO.getCatalogItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Catalog item not found"));
        orderItem.setCatalogItem(catalogItem);
        orderItem.setName(catalogItem.getName());
        orderItem.setPrice(catalogItem.getPrice());
        orderItem.setItemType(OrderItem.ItemType.CATALOG);
        orderItem.setStatus(OrderItem.ItemStatus.APPROVED);
      } else {
        orderItem.setName(itemDTO.getCustomItemName());
        orderItem.setPrice(itemDTO.getCustomItemPrice());
        orderItem.setCustomDescription(itemDTO.getCustomItemDescription());
        orderItem.setItemType(OrderItem.ItemType.CUSTOM);
        orderItem.setStatus(OrderItem.ItemStatus.PENDING_APPROVAL);
      }

      orderItems.add(orderItem);
      totalAmount = (totalAmount + orderItem.getPrice()) * orderItem.getQuantity();
    }

    order.setOrderItems(orderItems);
    order.setTotalAmount(totalAmount);
    // Set a fixed delivery charge or calculate based on some logic
    order.setDeliveryCharge(100.00);

    Order savedOrder = orderRepository.save(order);
    OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);
    return orderDTO;
  }

  public List<Order> getOrdersByCustomer(UserRegistrationDTO customerDTO) {
    User customer = modelMapper.map(customerDTO, User.class);
    return orderRepository.findByCustomer(customer);
  }

  public Order getOrderById(Long id) {
    Optional<Order> order = orderRepository.findById(id);
    return order.orElseThrow(() -> new OrderNotFoundException(String.valueOf(id)));
  }
}
