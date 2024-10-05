package com.NomadNecessities.controller;

import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/admin/create")
  public ResponseEntity<?> createUser(@RequestBody UserRegistrationDTO userRequest) {
    UserRegistrationDTO user = userService.createUser(userRequest);
    return ResponseEntity.ok(user);
  }

  // Get user details
  @GetMapping("/{userId}")
  public ResponseEntity<UserRegistrationDTO> getUserDetails(@PathVariable Long userId) {
    UserRegistrationDTO user = userService.findUserById(userId);

    return ResponseEntity.ok(user);
  }
}
