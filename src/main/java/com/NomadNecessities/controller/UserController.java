package com.NomadNecessities.controller;

import com.NomadNecessities.dto.ApiResponse;
import com.NomadNecessities.dto.UserRegistrationDTO;
import com.NomadNecessities.exception.UserAlreadyExistsException;
import com.NomadNecessities.model.User;
import com.NomadNecessities.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired private UserService userService;

  @PostMapping("/admin/create")
  public ResponseEntity<ApiResponse<User>> createUser(
      @RequestBody @Valid UserRegistrationDTO userRequest) {
    try {
      User user = userService.createUser(userRequest);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new ApiResponse<>(user, "User created successfully", HttpStatus.CREATED.value()));
    } catch (UserAlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(new ApiResponse<>(null, e.getMessage(), HttpStatus.CONFLICT.value()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(
              new ApiResponse<>(
                  null, "An error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
  }

  // Get user details
  @GetMapping("/{userId}")
  public ResponseEntity<UserRegistrationDTO> getUserDetails(@PathVariable Long userId) {
    UserRegistrationDTO user = userService.findUserById(userId);

    return ResponseEntity.ok(user);
  }
}
