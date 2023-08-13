package com.example.healthcare.services;

import com.example.healthcare.model.User;
import com.example.healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    return userRepository.save(user);
  }
}
