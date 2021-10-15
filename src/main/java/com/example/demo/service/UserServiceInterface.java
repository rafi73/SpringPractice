package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserServiceInterface {
  User create(User user);
}

@Service
@Slf4j
class UserService implements UserServiceInterface {
  @Autowired private UserRepository userRepository;

  @Override
  public User create(User user) {
    user.setName("Set from Service");
    log.info("qq{}", user);

    User savedUser = userRepository.save(user);
    return savedUser;
  }
}
