package com.NomadNecessities.service;

import com.NomadNecessities.model.User;
import com.NomadNecessities.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired UserRepository userRepository;

  public User createUser(User userRequest) {
    return userRepository.save(userRequest);
  }

  public User findById(Long userId) {
    return userRepository
        .findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }

  //    public User findById(long id) {
  //        Optional<User> user = userRepository.findById(id);
  //        return user.orElse(null);
  //    }
}
