package com.example.demo.controllers;

import com.example.demo.expections.ApplicationException;
import com.example.demo.models.User;
import com.example.demo.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

interface UserControllerInterface {
  User create(User user, HttpServletRequest servletRequest);
}

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerInterface {
  @Autowired private UserServiceInterface userService;

  @PostMapping
  public User create(@RequestBody User user, HttpServletRequest servletRequest)
      throws ApplicationException, RuntimeException {
    // User newUser = userService.create(user);

    throw new ApplicationException("some exception");

    // return newUser;
  }
}
