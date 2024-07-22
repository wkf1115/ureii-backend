package com.ureii.ureiibackend.controller;

import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;
import com.ureii.ureiibackend.service.UserService;
import com.ureii.ureiibackend.validate.ValidationGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult<String> login(@Validated(ValidationGroups.Login.class) @RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/logout")
    public ResponseResult<String> logout(){
        return userService.logout();
    }

    @PostMapping("/register")
    public ResponseResult<String> register(@Validated(ValidationGroups.Register.class) @RequestBody User user){
        return userService.register(user);
    }

    @GetMapping("/getUserInfo")
    public ResponseResult<User> getUserInfo(){
        return userService.getUserInfo();
    }
}