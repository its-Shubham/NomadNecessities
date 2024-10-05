package com.NomadNecessities.service;

import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.exception.UserAlreadyExistsException;
import com.NomadNecessities.model.Address;
import com.NomadNecessities.model.Role;
import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.AddressRepository;
import com.NomadNecessities.repository.RoleRepository;
import com.NomadNecessities.repository.UserRepository;
import javax.management.relation.RoleNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ModelMapper modelMapper;
  private final AddressRepository addressRepository;

  public UserService(
      UserRepository userRepository,
      RoleRepository roleRepository,
      ModelMapper modelMapper,
      AddressRepository addressRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
    this.addressRepository = addressRepository;
  }

  public User createUser(UserRegistrationDTO userRequest) throws RoleNotFoundException {
    // Check if the user already exists
    if (userRepository.findByPhoneNumber(userRequest.getPhoneNumber()).isPresent()
        || userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
      throw new UserAlreadyExistsException("User with this phone number or email already exists.");
    }

    // Map the incoming request to the User entity
    User user = modelMapper.map(userRequest, User.class);

    // Handle role assignment
    Role role =
        roleRepository
            .findById(userRequest.getRole())
            .orElseThrow(() -> new RoleNotFoundException("Role not found"));
    user.setRole(role);

    // Handle address saving if provided
    if (userRequest.getAddress() != null) {
      Address address = modelMapper.map(userRequest.getAddress(), Address.class);
      Address savedAddress = addressRepository.save(address);
      user.setAddress(savedAddress);
    }

    // Save the user
    User savedUser = userRepository.save(user);

    //    UserRegistrationDTO userRegistrationDTO = modelMapper.map(savedUser,
    // UserRegistrationDTO.class);
    //    userRegistrationDTO.setPassword(null);
    user.setPassword(null);
    return user;
  }

  public UserRegistrationDTO findUserById(Long userId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    UserRegistrationDTO userDTO = modelMapper.map(user, UserRegistrationDTO.class);
    userDTO.setPassword(null);
    return userDTO;
  }

  public UserRegistrationDTO findById(Long userId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    UserRegistrationDTO userDTO = modelMapper.map(user, UserRegistrationDTO.class);
    userDTO.setPassword(null);
    return userDTO;
  }

  //    public User findById(long id) {
  //        Optional<User> user = userRepository.findById(id);
  //        return user.orElse(null);
  //    }
}
