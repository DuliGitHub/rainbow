package com.dl.msprovideruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dl.msprovideruserapi.entity.User;
import com.dl.msprovideruser.repository.UserRepository;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private Registration registration;

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = userRepository.findOne(id);
    return findOne;
  }
  
  @GetMapping("/getIpAndPort")
  public String findById() {
	  return registration.getHost() + ":" + registration.getPort();
  }
}
