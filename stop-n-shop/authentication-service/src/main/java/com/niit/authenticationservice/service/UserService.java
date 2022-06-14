package com.niit.authenticationservice.service;

import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.exception.UserNotFoundException;

import java.util.List;


public interface UserService {

  public User saveUser(User user) throws UserAlreadyExistsException;
  public User findByEmailAndPassword(String email , String password) throws UserNotFoundException;
  public List<User> getAllUsers();
  User getUserByEmail(String email) throws  UserNotFoundException;
  boolean updateUser(User user) throws UserNotFoundException;
  boolean patchUser(User user) throws UserNotFoundException;

//  boolean validateUser(String username, String password) throws UserNotFoundException;

}
