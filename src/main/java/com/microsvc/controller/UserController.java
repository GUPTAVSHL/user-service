package com.microsvc.controller;

import com.microsvc.entity.User;
import com.microsvc.services.UserService;
import com.microsvc.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getService(){
        return "Running User-Service!!";
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        log.info("Inside the UserController:: saveUser()::");
        return userService.saveUser(user);
    }

    @GetMapping("/{userId}")
    public ResponseVO getUserById(@PathVariable("userId")Long userId){
        log.info("Inside the UserController:: getUserById()::");
        return userService.findByUserId(userId);
    }
}
