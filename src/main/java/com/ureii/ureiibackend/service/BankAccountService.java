package com.ureii.ureiibackend.service;


import com.ureii.ureiibackend.model.BankAccount;
import com.ureii.ureiibackend.model.ResponseResult;
import com.ureii.ureiibackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {


    // By default, we just have one account
    ResponseResult<BankAccount> getBankAccount(Long ownerId);

    ResponseResult<String> createBankAccount(BankAccount bankAccount, Long userId);
}
