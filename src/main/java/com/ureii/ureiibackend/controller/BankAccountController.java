package com.ureii.ureiibackend.controller;


import com.ureii.ureiibackend.model.BankAccount;
import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;
import com.ureii.ureiibackend.service.BankAccountService;
import com.ureii.ureiibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private UserService userService;


    @GetMapping("/getInfo")
    public ResponseResult<BankAccount> getAccountInfo(){
        ResponseResult<User> nowUser = userService.getUserInfo();
        if(nowUser.getCode() != 200) return new ResponseResult<>(nowUser.getCode(), "Something wrong! Could not get user info");
        return bankAccountService.getBankAccount(nowUser.getData().getId());
    }

    @PostMapping("/create")
    public ResponseResult<String> createBankAccount(@RequestBody BankAccount bankAccount) {
        ResponseResult<User> nowUser = userService.getUserInfo();
        if(nowUser.getCode() != 200) return new ResponseResult<>(nowUser.getCode(), "Something wrong! Could not get user info");
        return bankAccountService.createBankAccount(bankAccount, nowUser.getData().getId());
    }
}
