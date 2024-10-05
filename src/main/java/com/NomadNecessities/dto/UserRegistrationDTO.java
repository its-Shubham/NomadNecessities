package com.NomadNecessities.dto;

import com.NomadNecessities.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Phone number is required")
  @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
  private String phoneNumber;

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters long")
  private String password;

  private Long role;

  private Address address;
}
