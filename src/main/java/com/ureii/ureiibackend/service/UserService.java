package com.ureii.ureiibackend.service;

import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;

public interface UserService {
    ResponseResult<String> login(User user);
    ResponseResult<String> logout();
    ResponseResult<String> register(User user);

    ResponseResult<User> getUserInfo();
}
