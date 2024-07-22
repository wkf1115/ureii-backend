package com.ureii.ureiibackend.service;

import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;

public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
}
