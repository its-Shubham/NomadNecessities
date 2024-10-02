package com.NomadNecessities.controller;

import com.NomadNecessities.model.User;
import com.NomadNecessities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/admin/create")
  public ResponseEntity<?> createUser(@RequestBody User userRequest) {
    User user = userService.createUser(userRequest);
    return ResponseEntity.ok(user);
  }

  // Get user details
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
    User user = userService.findById(userId);
    return ResponseEntity.ok(user);
  }
}
