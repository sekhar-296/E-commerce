package com.niit.authenticationservice.controller;


import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.service.UserService;
import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserNotFoundException;
import com.niit.authenticationservice.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequestMapping("/api/v1/")
//@CrossOrigin("*")
@RestController
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;


    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        Map<String, String> map = null;
        try {
            User userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());

            if (userObj.getEmail().equals(user.getEmail())) {

                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

//  @GetMapping("/loginGoogle")
//  public ResponseEntity loginWithGoogle(@AuthenticationPrincipal OAuth2User principle){
//    Map<String, String> token = null;
//    User obj=new User();
//    try{
//      Map<String,Object> map= Collections.singletonMap("email",principle.getAttribute("email"));
//      String email=map.get("email").toString();
//      System.out.println(email);
//      obj.setEmail(email);
//      if(email!=null || email!="" ){
//        token=securityTokenGenerator.generateToken(obj);
//      }
//      token.put("email",email);
//      responseEntity=new ResponseEntity(token,HttpStatus.OK);
//    }
//    catch (Exception e){
//      e.printStackTrace();
//    }
//    return responseEntity;
//  }

    // first step - register the user
    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        User createdUser = null;
        Map<String, String> map = new HashMap<>();
        try {
            createdUser = userService.saveUser(user);
            map.put("message", "user created");
            return responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            return responseEntity = new ResponseEntity("user already exists", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/api/v1/userservice/users")
    public ResponseEntity getAllUsers(HttpServletRequest request) {

        List<User> list = userService.getAllUsers();
        responseEntity = new ResponseEntity(list, HttpStatus.OK);
        return responseEntity;

    }

    @GetMapping("user/{email}")
    public ResponseEntity<?> getUserByMobileNo(@PathVariable("email") String email) throws UserNotFoundException {

        try {

            User userObj = userService.getUserByEmail(email);
            responseEntity = new ResponseEntity(userObj, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception exception) {
            String ex = exception.getMessage();
            System.out.println(exception.getMessage());
            responseEntity = new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user")
    public ResponseEntity<?> updateProduct(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> map = new HashMap<>();
        try {
            userService.updateUser(user);
            map.put("message", "updated successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
//  @GetMapping("code/{pin}")
//  public List<Object> getCities(@PathVariable("pin") String pin){
//    String url="https://api.postalpincode.in/pincode/"+pin;
//    RestTemplate restTemplate=new RestTemplate();
//    Object [] cities= restTemplate.getForObject(url,Object[].class);
//    return Arrays.asList(cities[0]);
//  }

    @GetMapping("code/{pin}")
    public Object getCities(@PathVariable("pin") String pin) {
        String url = "https://api.postalpincode.in/pincode/" + pin;
        RestTemplate restTemplate = new RestTemplate();
        Object cities = restTemplate.getForObject(url, Object.class);
        return cities;
    }

    @PatchMapping("user1")
    public ResponseEntity<?> patchDetail(@RequestBody User user) throws UserNotFoundException {
        System.out.println(user);
        Map<String, String> map = new HashMap<>();
        try {
            userService.patchUser(user);
            map.put("message", "patched successfully");
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
