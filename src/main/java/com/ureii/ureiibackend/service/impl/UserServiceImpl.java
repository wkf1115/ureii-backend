package com.ureii.ureiibackend.service.impl;

import com.ureii.ureiibackend.model.LoginUser;
import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;
import com.ureii.ureiibackend.repository.UserRepository;
import com.ureii.ureiibackend.service.UserService;
import com.ureii.ureiibackend.util.JwtUtil;
import com.ureii.ureiibackend.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("invalid username or password");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult<>(200,"login success",map);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult<>(200,"logout success");
    }

    @Override
    public ResponseResult register(User user) {
        // 检查用户名是否存在
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseResult<>(400, "Username already exists");
        }

        // 检查电子邮件是否存在
        if (userRepository.findByEmail(user.getUsername()) != null) {
            return new ResponseResult<>(400, "Email already exists");
        }

        // 创建新用户并加密密码
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setRole("user");

        // 保存用户
        userRepository.save(newUser);

        return new ResponseResult<>(200, "User registered successfully");
    }

    @Override
    public ResponseResult getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        if (null == userId) return new ResponseResult<>(400, "There is something wrong with your account, please contact admin");

        User user = userRepository.findById(userId).get();

        return new ResponseResult<User>(200, user);
    }
}
