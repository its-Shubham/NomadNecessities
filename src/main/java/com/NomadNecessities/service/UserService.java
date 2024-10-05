package com.NomadNecessities.service;

import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  private ModelMapper modelMapper;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserRegistrationDTO createUser(UserRegistrationDTO userRequest) {
    User user = modelMapper.map(userRequest, User.class);
    User savedUser = userRepository.save(user);
    UserRegistrationDTO userRegistrationDTO = modelMapper.map(savedUser, UserRegistrationDTO.class);
    userRegistrationDTO.setPassword(null);
    return userRegistrationDTO;
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
